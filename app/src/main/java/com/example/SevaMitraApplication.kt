package com.example

import android.app.Application
import com.example.data.repository.DispatchRepository
import com.example.util.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SevaMitraApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val repository: DispatchRepository by lazy {
        val session = SessionManager(this)
        val initialPhone = if (session.isLoggedIn()) session.getUserPhone() else ""
        DispatchRepository(
            context = this,
            initialUserPhone = initialPhone
        )
    }

    override fun onCreate() {
        super.onCreate()
        val session = SessionManager(this)
        if (session.isLoggedIn() && session.getUserPhone().isNotBlank()) {
            applicationScope.launch {
                repository.ensureDefaultCategoriesForCurrentUser()
            }
        }
    }
}
