package com.example

import android.app.Application
import androidx.work.Configuration
import com.example.data.firebase.FirestoreSyncManager
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
        DispatchRepository(context = this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    override fun onCreate() {
        super.onCreate()

        // 0. Initialize Firebase App
        try {
            if (com.google.firebase.FirebaseApp.getApps(this).isEmpty()) {
                val options = com.google.firebase.FirebaseOptions.Builder()
                    .setApplicationId("1:867809848034:web:3356dd2e7908f6c1050cbb")
                    .setApiKey("AIzaSyD3oNqEPV_VAbzwB7eh0jUTCxo2YT4jBIk")
                    .setProjectId("hurifix")
                    .setStorageBucket("hurifix.firebasestorage.app")
                    .setGcmSenderId("867809848034")
                    .setDatabaseUrl("https://hurifix-default-rtdb.firebaseio.com")
                    .build()
                com.google.firebase.FirebaseApp.initializeApp(this, options)
            }
        } catch (_: Throwable) {}

        // 1. Initialize Network Monitor & Sound Manager
        try {
            com.example.util.NetworkMonitor.initialize(this)
            com.example.util.SoundManager.initialize(this)
        } catch (_: Throwable) {}

        // 2. Ensure categories and defaults
        applicationScope.launch {
            try {
                repository.ensureDefaultCategoriesForCurrentUser()
                FirestoreSyncManager.getInstance(this@SevaMitraApplication).seedMasterAdmins()
            } catch (_: Throwable) {}
        }

        // 3. Start Realtime Cloud Sync Listeners
        try {
            FirestoreSyncManager.getInstance(this).startRealtimeSyncListeners()
        } catch (_: Throwable) {}

        // 4. Schedule WorkManager Periodic Cloud Sync (every 15 mins)
        try {
            SyncWorker.schedulePeriodicSync(this)
        } catch (_: Throwable) {}

        // 5. Initialize scheduled Google Drive Auto-Backup if enabled
        try {
            val sessionManager = SessionManager(this)
            if (sessionManager.isAutoBackupEnabled()) {
                com.example.util.AutoBackupWorker.scheduleAutoBackup(
                    context = this,
                    isEnabled = true,
                    frequency = sessionManager.getAutoBackupFrequency()
                )
            }
        } catch (_: Throwable) {}
    }
}
