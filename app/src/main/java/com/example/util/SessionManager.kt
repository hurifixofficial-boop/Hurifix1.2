package com.example.util

import android.content.Context
import android.content.SharedPreferences
import com.example.data.firebase.FirestoreSyncManager
import com.example.data.model.HurifixUser

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("hurifix_session", Context.MODE_PRIVATE)
    private val syncManager = FirestoreSyncManager.getInstance(context)

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)
    fun getUserPhone(): String = prefs.getString("user_phone", "") ?: ""
    fun getUserName(): String = prefs.getString("user_name", "") ?: ""
    fun getUserRole(): String = prefs.getString("user_role", "STAFF") ?: "STAFF"
    fun isDarkModeEnabled(): Boolean = prefs.getBoolean("dark_mode", false)

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
    }

    suspend fun login(phone: String, password: String): Result<String> {
        val res = syncManager.login(phone, password)
        return res.map { user ->
            saveSession(user)
            user.name
        }
    }

    suspend fun loginWithOtp(phone: String): Result<String> {
        val res = syncManager.loginWithOtp(phone)
        return res.map { user ->
            saveSession(user)
            user.name
        }
    }

    suspend fun recoverAdminAccess(phone: String, secretKey: String, lostPhoneToBlock: String? = null): Result<String> {
        val res = syncManager.recoverAdminAccess(phone, secretKey, lostPhoneToBlock)
        return res.map { user ->
            saveSession(user)
            user.name
        }
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    private fun saveSession(user: HurifixUser) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("user_phone", user.phone)
            .putString("user_name", user.name)
            .putString("user_role", user.role)
            .apply()
    }
}
