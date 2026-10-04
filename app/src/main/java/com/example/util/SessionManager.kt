package com.example.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("hurifix_auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_PHONE = "user_phone"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_USER_PHOTO_URI = "user_photo_uri"
        private const val KEY_DEFAULT_SEEDED = "default_users_seeded"
        private const val KEY_DARK_MODE = "dark_mode_enabled"
    }

    fun isDarkModeEnabled(): Boolean = prefs.getBoolean(KEY_DARK_MODE, false)

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun getUserPhotoUri(): String? = prefs.getString(KEY_USER_PHOTO_URI, null)

    fun getUserRole(): String = prefs.getString(KEY_USER_ROLE, "Hurifix Admin & Operations") ?: "Hurifix Admin & Operations"

    fun updateAdminProfile(name: String, phone: String, role: String = "Hurifix Admin & Operations", photoUri: String? = null) {
        prefs.edit().apply {
            putString(KEY_USER_NAME, name.trim())
            putString(KEY_USER_PHONE, phone.trim())
            putString(KEY_USER_ROLE, role.trim())
            putString(KEY_USER_PHOTO_URI, photoUri)
            apply()
        }
    }

    init {
        // Pre-seed demo administrator account if not present
        if (!prefs.getBoolean(KEY_DEFAULT_SEEDED, false)) {
            prefs.edit().apply {
                putString("user_pwd_9876543210", "admin123")
                putString("user_name_9876543210", "Hurifix Admin")
                putBoolean(KEY_DEFAULT_SEEDED, true)
                apply()
            }
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getUserName(): String = prefs.getString(KEY_USER_NAME, "Hurifix Partner") ?: "Hurifix Partner"

    fun getUserPhone(): String = prefs.getString(KEY_USER_PHONE, "") ?: ""

    fun login(phone: String, password: String):Result<String> {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            return Result.failure(Exception("Please enter a valid 10-digit mobile number"))
        }
        val storedPassword = prefs.getString("user_pwd_$cleanPhone", null)
        if (storedPassword == null) {
            return Result.failure(Exception("This mobile number is not registered. Please create a new account."))
        }
        if (storedPassword != password) {
            return Result.failure(Exception("Incorrect password. Please enter the correct password."))
        }

        val name = prefs.getString("user_name_$cleanPhone", "Hurifix Partner") ?: "Hurifix Partner"
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_PHONE, cleanPhone)
            putString(KEY_USER_NAME, name)
            apply()
        }
        return Result.success(name)
    }

    fun register(name: String, phone: String, password: String): Result<String> {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            return Result.failure(Exception("Please enter a valid 10-digit mobile number"))
        }
        if (name.isBlank()) {
            return Result.failure(Exception("Please enter your name"))
        }
        if (password.length < 4) {
            return Result.failure(Exception("Password must be at least 4 characters long"))
        }
        if (prefs.contains("user_pwd_$cleanPhone")) {
            return Result.failure(Exception("This mobile number is already registered. Please log in."))
        }

        prefs.edit().apply {
            putString("user_pwd_$cleanPhone", password)
            putString("user_name_$cleanPhone", name.trim())
            // Auto login after sign up
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_PHONE, cleanPhone)
            putString(KEY_USER_NAME, name.trim())
            apply()
        }
        return Result.success(name.trim())
    }

    fun logout() {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, false)
            putString(KEY_USER_PHONE, "")
            putString(KEY_USER_NAME, "")
            apply()
        }
    }
}
