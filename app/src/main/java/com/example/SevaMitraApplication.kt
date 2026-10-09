package com.example

import android.app.Application
import androidx.work.Configuration
import com.example.data.firebase.FirestoreSyncManager
import com.example.data.local.AppDatabase
import com.example.data.repository.DispatchRepository
import com.example.util.SessionManager
import com.example.util.SyncWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SevaMitraApplication : Application(), Configuration.Provider {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val repository: DispatchRepository by lazy {
        val db = AppDatabase.getDatabase(this)
        DispatchRepository(
            context = this,
            initialUserPhone = "",
            fallbackExpertDao = db.expertDao(),
            fallbackJobDao = db.customerJobDao(),
            fallbackCategoryDao = db.expertCategoryDao(),
            fallbackTechnicianDao = db.technicianDao(),
            fallbackCustomerDao = db.customerDao()
        )
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()

        // 1. Ensure categories and defaults
        applicationScope.launch {
            try {
                repository.ensureDefaultCategoriesForCurrentUser()
                FirestoreSyncManager.getInstance(this@SevaMitraApplication).seedMasterAdmins()
            } catch (_: Throwable) {}
        }

        // 2. Start Realtime Cloud Sync Listeners
        try {
            FirestoreSyncManager.getInstance(this).startRealtimeSyncListeners()
        } catch (_: Throwable) {}

        // 3. Schedule WorkManager Periodic Cloud Sync (every 15 mins)
        try {
            SyncWorker.schedulePeriodicSync(this)
        } catch (_: Throwable) {}
    }
}
