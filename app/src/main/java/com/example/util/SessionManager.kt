package com.example.util

import android.content.Context
import android.content.SharedPreferences
import com.example.data.firebase.FirestoreSyncManager
import com.example.data.model.HurifixUser

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("hurifix_session", Context.MODE_PRIVATE)
    private val syncManager: FirestoreSyncManager = FirestoreSyncManager.getInstance(context)

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)
    fun getUserPhone(): String = prefs.getString("user_phone", "") ?: ""
    fun getUserName(): String = prefs.getString("user_name", "") ?: ""
    fun getUserRole(): String = prefs.getString("user_role", HurifixUser.ROLE_STAFF) ?: HurifixUser.ROLE_STAFF
    fun getUserDesignationTag(): String = prefs.getString("user_designation", "Staff Member") ?: "Staff Member"
    fun getUserPhotoUri(): String = prefs.getString("user_photo_uri", "") ?: ""
    fun getUserPassword(): String = prefs.getString("user_password", "") ?: ""
    fun isDarkModeEnabled(): Boolean = prefs.getBoolean("dark_mode", false)

    fun isAdmin(): Boolean = getUserRole().equals(HurifixUser.ROLE_ADMIN, ignoreCase = true)
    fun canManageOrders(): Boolean = isAdmin() || prefs.getBoolean("can_manage_orders", true)
    fun canAddExperts(): Boolean = isAdmin() || prefs.getBoolean("can_add_experts", true)
    fun canDeleteOrders(): Boolean = isAdmin() || prefs.getBoolean("can_delete_orders", false)
    fun canExportReports(): Boolean = isAdmin() || prefs.getBoolean("can_export_reports", false)
    fun isViewOnly(): Boolean = !isAdmin() && prefs.getBoolean("view_only", false)

    fun isAutoBackupEnabled(): Boolean = prefs.getBoolean("auto_backup_enabled", false)
    fun getAutoBackupFrequency(): String = prefs.getString("auto_backup_frequency", "DAILY") ?: "DAILY"
    fun getLastBackupTimestamp(): Long = prefs.getLong("last_backup_timestamp", 0L)
    fun getLastBackupStatus(): String = prefs.getString("last_backup_status", "Never") ?: "Never"

    fun setAutoBackupEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("auto_backup_enabled", enabled).apply()
    }

    fun setAutoBackupFrequency(frequency: String) {
        prefs.edit().putString("auto_backup_frequency", frequency).apply()
    }

    fun setLastBackupTimestamp(timestamp: Long) {
        prefs.edit().putLong("last_backup_timestamp", timestamp).apply()
    }

    fun setLastBackupStatus(status: String) {
        prefs.edit().putString("last_backup_status", status).apply()
    }

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

    suspend fun updatePassword(newPassword: String): Result<Unit> {
        val phone = getUserPhone()
        val res = syncManager.updateUserPassword(phone, newPassword)
        if (res.isSuccess) {
            prefs.edit().putString("user_password", newPassword).apply()
        }
        return res
    }

    suspend fun deleteCurrentUserAccount(): Result<Unit> {
        val phone = getUserPhone()
        val res = syncManager.deleteUser(phone)
        if (res.isSuccess) {
            logout()
        }
        return res
    }

    fun updateAdminProfile(name: String, phone: String, role: String, photoUri: String?) {
        prefs.edit()
            .putString("user_name", name)
            .putString("user_phone", phone)
            .putString("user_designation", role)
            .putString("user_photo_uri", photoUri ?: "")
            .apply()
    }

    fun updateUserProfile(name: String, phone: String, role: String, photoUri: String?) {
        updateAdminProfile(name, phone, role, photoUri)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun saveUserSession(user: HurifixUser) {
        saveSession(user)
    }

    private fun saveSession(user: HurifixUser) {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("user_phone", user.phone)
            .putString("user_name", user.name)
            .putString("user_password", user.password)
            .putString("user_role", user.role)
            .putString("user_designation", user.designation_tag)
            .putString("user_photo_uri", user.profile_pic_url ?: "")
            .putBoolean("can_manage_orders", user.can_manage_orders)
            .putBoolean("can_add_experts", user.can_add_experts)
            .putBoolean("can_delete_orders", user.can_delete_orders)
            .putBoolean("can_export_reports", user.can_export_reports)
            .putBoolean("view_only", user.view_only)
            .apply()
    }
}
