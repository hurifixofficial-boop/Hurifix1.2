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
    fun getUserDesignationTag(): String = prefs.getString("user_designation", getUserRole()) ?: "STAFF"
    fun getUserPhotoUri(): String? = prefs.getString("user_photo_uri", null)
    fun getUserPassword(): String = prefs.getString("user_password", "") ?: ""

    fun isAdmin(): Boolean = getUserRole().equals("ADMIN", ignoreCase = true) || getUserRole().equals("Admin", ignoreCase = true)
    fun canManageOrders(): Boolean = true
    fun canDeleteOrders(): Boolean = true
    fun isViewOnly(): Boolean = false
    fun canAddExperts(): Boolean = true
    fun canExportReports(): Boolean = true

    fun isAutoBackupEnabled(): Boolean = prefs.getBoolean("auto_backup", true)
    fun setAutoBackupEnabled(enabled: Boolean) { prefs.edit().putBoolean("auto_backup", enabled).apply() }
    fun getAutoBackupFrequency(): String = prefs.getString("auto_backup_freq", "Daily") ?: "Daily"
    fun setAutoBackupFrequency(freq: String) { prefs.edit().putString("auto_backup_freq", freq).apply() }
    fun getLastBackupTimestamp(): Long = prefs.getLong("last_backup_time", 0L)
    fun setLastBackupTimestamp(time: Long) { prefs.edit().putLong("last_backup_time", time).apply() }
    fun getLastBackupStatus(): String = prefs.getString("last_backup_status", "Never") ?: "Never"
    fun setLastBackupStatus(status: String) { prefs.edit().putString("last_backup_status", status).apply() }

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

    fun saveUserSession(user: HurifixUser) {
        saveSession(user)
    }

    suspend fun updatePassword(newPassword: String): Result<Unit> {
        return Result.success(Unit)
    }

    suspend fun deleteCurrentUserAccount(): Result<Unit> {
        return Result.success(Unit)
    }

    fun updateAdminProfile(name: String, phone: String, designation: String, photoUri: String?) {
        prefs.edit()
            .putString("user_name", name)
            .putString("user_phone", phone)
            .putString("user_designation", designation)
            .putString("user_photo_uri", photoUri)
            .apply()
    }

    fun updateUserProfile(name: String, phone: String, designation: String, photoUri: String?) {
        updateAdminProfile(name, phone, designation, photoUri)
    }

    private fun saveSession(user: HurifixUser) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("user_phone", user.phone)
            .putString("user_name", user.name)
            .putString("user_role", user.role)
            .putString("user_designation", user.displayDesignation)
            .putString("user_photo_uri", user.profile_pic_url)
            .apply()
    }
}
