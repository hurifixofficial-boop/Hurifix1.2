package com.example.util

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.HurifixUser

class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("hurifix_auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_PHONE = "user_phone"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_USER_PASSWORD = "user_password"
        private const val KEY_USER_PHOTO_URI = "user_photo_uri"
        private const val KEY_DARK_MODE = "dark_mode_enabled"

        // RBAC Permissions
        private const val KEY_CAN_ADD_EXPERTS = "can_add_experts"
        private const val KEY_CAN_MANAGE_ORDERS = "can_manage_orders"
        private const val KEY_CAN_ADD_CUSTOMERS = "can_add_customers"
        private const val KEY_VIEW_ONLY = "view_only"
    }

    fun isDarkModeEnabled(): Boolean = prefs.getBoolean(KEY_DARK_MODE, false)

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getUserName(): String = prefs.getString(KEY_USER_NAME, "Hurifix User") ?: "Hurifix User"

    fun getUserPhone(): String = prefs.getString(KEY_USER_PHONE, "") ?: ""

    fun getUserRole(): String = prefs.getString(KEY_USER_ROLE, HurifixUser.ROLE_STAFF) ?: HurifixUser.ROLE_STAFF

    fun getUserPassword(): String = prefs.getString(KEY_USER_PASSWORD, "") ?: ""

    fun getUserPhotoUri(): String? = prefs.getString(KEY_USER_PHOTO_URI, null)

    fun isAdmin(): Boolean {
        val role = getUserRole()
        val phone = getUserPhone()
        return role.equals(HurifixUser.ROLE_ADMIN, ignoreCase = true) || HurifixUser.isMasterAdminPhone(phone)
    }

    fun canAddExperts(): Boolean {
        if (isAdmin()) return true
        if (isViewOnly()) return false
        return prefs.getBoolean(KEY_CAN_ADD_EXPERTS, true)
    }

    fun canManageOrders(): Boolean {
        if (isAdmin()) return true
        if (isViewOnly()) return false
        return prefs.getBoolean(KEY_CAN_MANAGE_ORDERS, true)
    }

    fun canAddCustomers(): Boolean {
        if (isAdmin()) return true
        if (isViewOnly()) return false
        return prefs.getBoolean(KEY_CAN_ADD_CUSTOMERS, true)
    }

    fun isViewOnly(): Boolean {
        if (isAdmin()) return false
        return prefs.getBoolean(KEY_VIEW_ONLY, false)
    }

    fun saveUserSession(user: HurifixUser) {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_PHONE, user.phone)
            putString(KEY_USER_NAME, user.name)
            putString(KEY_USER_ROLE, user.role)
            putString(KEY_USER_PASSWORD, user.password)
            putString(KEY_USER_PHOTO_URI, user.photo_uri)
            putBoolean(KEY_CAN_ADD_EXPERTS, user.can_add_experts)
            putBoolean(KEY_CAN_MANAGE_ORDERS, user.can_manage_orders)
            putBoolean(KEY_CAN_ADD_CUSTOMERS, user.can_add_customers)
            putBoolean(KEY_VIEW_ONLY, user.view_only)
            apply()
        }
    }

    fun updatePermissions(
        canAddExperts: Boolean,
        canManageOrders: Boolean,
        canAddCustomers: Boolean,
        viewOnly: Boolean
    ) {
        prefs.edit().apply {
            putBoolean(KEY_CAN_ADD_EXPERTS, canAddExperts)
            putBoolean(KEY_CAN_MANAGE_ORDERS, canManageOrders)
            putBoolean(KEY_CAN_ADD_CUSTOMERS, canAddCustomers)
            putBoolean(KEY_VIEW_ONLY, viewOnly)
            apply()
        }
    }

    fun updatePassword(newPassword: String) {
        prefs.edit().putString(KEY_USER_PASSWORD, newPassword).apply()
    }

    fun updateAdminProfile(name: String, phone: String, role: String = "Hurifix Admin", photoUri: String? = null) {
        prefs.edit().apply {
            putString(KEY_USER_NAME, name.trim())
            if (phone.isNotBlank()) putString(KEY_USER_PHONE, phone.replace(Regex("[^0-9]"), ""))
            putString(KEY_USER_ROLE, role.trim())
            putString(KEY_USER_PHOTO_URI, photoUri)
            apply()
        }
    }

    fun logout() {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, false)
            putString(KEY_USER_PHONE, "")
            putString(KEY_USER_NAME, "")
            remove(KEY_USER_ROLE)
            remove(KEY_USER_PASSWORD)
            remove(KEY_USER_PHOTO_URI)
            remove(KEY_CAN_ADD_EXPERTS)
            remove(KEY_CAN_MANAGE_ORDERS)
            remove(KEY_CAN_ADD_CUSTOMERS)
            remove(KEY_VIEW_ONLY)
            apply()
        }
    }
}
