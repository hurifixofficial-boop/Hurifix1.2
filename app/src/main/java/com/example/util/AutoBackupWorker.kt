package com.example.util

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Automated WorkManager worker that generates scheduled lightweight JSON backups
 * and stores them in the internal backup vault for Google Drive and administrative safety.
 */
class AutoBackupWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val sessionManager = SessionManager(applicationContext)
        if (!sessionManager.isAutoBackupEnabled()) {
            return@withContext Result.success()
        }

        try {
            val db = AppDatabase.getDatabase(applicationContext)
            val jobs = db.customerJobDao().getAllJobs().first()
            val experts = db.expertDao().getAllExperts().first()
            val categories = db.expertCategoryDao().getAllCategories().first()

            val jsonString = BackupRestoreHelper.createBackupJson(jobs, experts, categories)
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "Hurifix_AutoBackup_$timeStamp.json"

            val backupDir = File(applicationContext.filesDir, "auto_backups").apply { mkdirs() }
            val backupFile = File(backupDir, fileName)
            backupFile.writeText(jsonString)

            // Also keep latest reference
            val latestFile = File(backupDir, "Hurifix_AutoBackup_Latest.json")
            latestFile.writeText(jsonString)

            // Keep only latest 5 backup files to manage storage efficiently
            backupDir.listFiles()?.filter { it.name.startsWith("Hurifix_AutoBackup_2") }
                ?.sortedByDescending { it.lastModified() }
                ?.drop(5)
                ?.forEach { it.delete() }

            val statusMsg = "Auto-backup saved successfully (${jobs.size} orders, ${experts.size} experts)"
            sessionManager.setLastBackupTimestamp(System.currentTimeMillis())
            sessionManager.setLastBackupStatus(statusMsg)
            Log.i(TAG, "AutoBackupWorker completed: $statusMsg")

            Result.success()
        } catch (e: Throwable) {
            Log.e(TAG, "AutoBackupWorker failed: ${e.message}", e)
            val errMsg = "Auto-backup failed: ${e.localizedMessage}"
            sessionManager.setLastBackupStatus(errMsg)
            Result.retry()
        }
    }

    companion object {
        private const val TAG = "AutoBackupWorker"
        private const val UNIQUE_PERIODIC_WORK_NAME = "HurifixScheduledGoogleDriveAutoBackup"
        private const val UNIQUE_ONETIME_WORK_NAME = "HurifixImmediateAutoBackup"

        fun scheduleAutoBackup(context: Context, isEnabled: Boolean, frequency: String) {
            try {
                val workManager = WorkManager.getInstance(context)
                if (!isEnabled) {
                    workManager.cancelUniqueWork(UNIQUE_PERIODIC_WORK_NAME)
                    Log.i(TAG, "Auto-backup schedule cancelled")
                    return
                }

                val (interval, timeUnit) = when (frequency.uppercase(Locale.getDefault())) {
                    "WEEKLY" -> 7L to TimeUnit.DAYS
                    else -> 24L to TimeUnit.HOURS // Default: Daily
                }

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .build()

                val backupRequest = PeriodicWorkRequestBuilder<AutoBackupWorker>(
                    interval, timeUnit
                ).setConstraints(constraints).build()

                workManager.enqueueUniquePeriodicWork(
                    UNIQUE_PERIODIC_WORK_NAME,
                    ExistingPeriodicWorkPolicy.UPDATE,
                    backupRequest
                )
                Log.i(TAG, "Auto-backup periodic work scheduled: $interval $timeUnit")
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to schedule AutoBackup: ${e.message}")
            }
        }

        fun runImmediateBackup(context: Context) {
            try {
                val workRequest = OneTimeWorkRequestBuilder<AutoBackupWorker>().build()
                WorkManager.getInstance(context).enqueueUniqueWork(
                    UNIQUE_ONETIME_WORK_NAME,
                    ExistingWorkPolicy.REPLACE,
                    workRequest
                )
                Log.i(TAG, "Immediate AutoBackup work enqueued")
            } catch (e: Throwable) {
                Log.w(TAG, "Failed to trigger immediate AutoBackup: ${e.message}")
            }
        }
    }
}
