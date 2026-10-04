package com.example.data.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.data.local.AppDatabase
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertCategoryEntity
import com.example.data.model.ExpertEntity
import com.example.data.remote.FirestoreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

enum class SyncStatus {
    IDLE,
    SYNCING,
    SUCCESS,
    ERROR
}

class SyncManager(private val context: Context) {

    private val firestoreService = FirestoreService(context)
    private val database = AppDatabase.getDatabase(context)

    private val _syncStatus = MutableStateFlow(SyncStatus.IDLE)
    val syncStatus: Flow<SyncStatus> = _syncStatus.asStateFlow()

    private val _lastSyncTimestamp = MutableStateFlow(System.currentTimeMillis())
    val lastSyncTimestamp: Flow<Long> = _lastSyncTimestamp.asStateFlow()

    val unsyncedJobsCount: Flow<Int> = database.customerJobDao().getUnsyncedJobsCount()
    val unsyncedExpertsCount: Flow<Int> = database.expertDao().getUnsyncedExpertsCount()

    companion object {
        private const val SYNC_WORK_NAME = "hurifix_background_sync_worker"

        fun scheduleBackgroundSync(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(30, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                SYNC_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest
            )
        }
    }

    /**
     * Performs a two-way synchronization between Room and Firebase Firestore
     * using the "Latest Timestamp Wins" conflict resolution algorithm.
     */
    suspend fun syncNow(): Result<String> = withContext(Dispatchers.IO) {
        _syncStatus.value = SyncStatus.SYNCING
        try {
            // 1. Sync Jobs
            val localJobs = database.customerJobDao().getAllJobsSync()
            val remoteJobs = firestoreService.fetchAllRemoteJobs()
            val localJobsMap = localJobs.associateBy { it.id }.toMutableMap()
            val remoteJobsMap = remoteJobs.associateBy { it.id }.toMutableMap()

            // Resolve conflicts: Latest Timestamp Wins
            for ((id, remoteJob) in remoteJobsMap) {
                val localJob = localJobsMap[id]
                if (localJob == null) {
                    database.customerJobDao().insertJob(remoteJob.copy(is_synced = true))
                } else {
                    if (remoteJob.last_updated > localJob.last_updated) {
                        database.customerJobDao().insertJob(remoteJob.copy(is_synced = true))
                    } else if (localJob.last_updated > remoteJob.last_updated) {
                        firestoreService.pushJobToFirestore(localJob)
                        database.customerJobDao().markJobSynced(id)
                    } else {
                        database.customerJobDao().markJobSynced(id)
                    }
                }
            }

            for ((id, localJob) in localJobsMap) {
                if (!remoteJobsMap.containsKey(id)) {
                    firestoreService.pushJobToFirestore(localJob)
                    database.customerJobDao().markJobSynced(id)
                }
            }

            // 2. Sync Experts
            val localExperts = database.expertDao().getAllExpertsSync()
            val remoteExperts = firestoreService.fetchAllRemoteExperts()
            val localExpertsMap = localExperts.associateBy { it.id }.toMutableMap()
            val remoteExpertsMap = remoteExperts.associateBy { it.id }.toMutableMap()

            for ((id, remoteExpert) in remoteExpertsMap) {
                val localExpert = localExpertsMap[id]
                if (localExpert == null) {
                    database.expertDao().insertExpert(remoteExpert.copy(is_synced = true))
                } else {
                    if (remoteExpert.last_updated > localExpert.last_updated) {
                        database.expertDao().insertExpert(remoteExpert.copy(is_synced = true))
                    } else if (localExpert.last_updated > remoteExpert.last_updated) {
                        firestoreService.pushExpertToFirestore(localExpert)
                        database.expertDao().markExpertSynced(id)
                    } else {
                        database.expertDao().markExpertSynced(id)
                    }
                }
            }

            for ((id, localExpert) in localExpertsMap) {
                if (!remoteExpertsMap.containsKey(id)) {
                    firestoreService.pushExpertToFirestore(localExpert)
                    database.expertDao().markExpertSynced(id)
                }
            }

            // 3. Sync Categories
            val localCategories = database.expertCategoryDao().getAllCategoriesSync()
            val remoteCategories = firestoreService.fetchAllRemoteCategories()
            val localCatNames = localCategories.associateBy { it.name.trim().lowercase() }.toMutableMap()
            val remoteCatNames = remoteCategories.associateBy { it.name.trim().lowercase() }.toMutableMap()

            for ((nameKey, remoteCat) in remoteCatNames) {
                val localCat = localCatNames[nameKey]
                if (localCat == null) {
                    database.expertCategoryDao().insertCategory(remoteCat.copy(is_synced = true))
                } else {
                    if (remoteCat.last_updated > localCat.last_updated) {
                        database.expertCategoryDao().insertCategory(remoteCat.copy(id = localCat.id, is_synced = true))
                    } else if (localCat.last_updated > remoteCat.last_updated) {
                        firestoreService.pushCategoryToFirestore(localCat)
                        database.expertCategoryDao().markCategorySynced(localCat.id)
                    } else {
                        database.expertCategoryDao().markCategorySynced(localCat.id)
                    }
                }
            }

            for ((nameKey, localCat) in localCatNames) {
                if (!remoteCatNames.containsKey(nameKey)) {
                    firestoreService.pushCategoryToFirestore(localCat)
                    database.expertCategoryDao().markCategorySynced(localCat.id)
                }
            }

            _lastSyncTimestamp.value = System.currentTimeMillis()
            _syncStatus.value = SyncStatus.SUCCESS
            Result.success("Cloud synchronization complete!")
        } catch (e: Exception) {
            _syncStatus.value = SyncStatus.ERROR
            Result.failure(e)
        }
    }
}

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val syncManager = SyncManager(applicationContext)
        val result = syncManager.syncNow()
        return if (result.isSuccess) {
            Result.success()
        } else {
            Result.retry()
        }
    }
}
