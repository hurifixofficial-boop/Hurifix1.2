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

    fun getUserPhotoUri(): String? {
        val phone = getUserPhone()
        return if (phone.isNotBlank()) {
            prefs.getString("user_photo_$phone", null) ?: prefs.getString(KEY_USER_PHOTO_URI, null)
        } else {
            prefs.getString(KEY_USER_PHOTO_URI, null)
        }
    }

    fun getUserRole(): String {
        val phone = getUserPhone()
        return if (phone.isNotBlank()) {
            prefs.getString("user_role_$phone", null) ?: prefs.getString(KEY_USER_ROLE, "Hurifix Partner & Operations") ?: "Hurifix Partner & Operations"
        } else {
            prefs.getString(KEY_USER_ROLE, "Hurifix Partner & Operations") ?: "Hurifix Partner & Operations"
        }
    }

    fun updateAdminProfile(name: String, phone: String, role: String = "Hurifix Partner & Operations", photoUri: String? = null) {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "").ifBlank { getUserPhone() }
        prefs.edit().apply {
            putString(KEY_USER_NAME, name.trim())
            putString(KEY_USER_PHONE, cleanPhone)
            putString(KEY_USER_ROLE, role.trim())
            putString(KEY_USER_PHOTO_URI, photoUri)
            if (cleanPhone.isNotBlank()) {
                putString("user_name_$cleanPhone", name.trim())
                putString("user_role_$cleanPhone", role.trim())
                putString("user_photo_$cleanPhone", photoUri)
            }
            apply()
        }
    }

    init {
        // Clean up any previously pre-seeded demo user
        if (prefs.contains("user_pwd_9876543210") || prefs.getBoolean(KEY_DEFAULT_SEEDED, false)) {
            prefs.edit().apply {
                remove("user_pwd_9876543210")
                remove("user_name_9876543210")
                remove("user_role_9876543210")
                remove(KEY_DEFAULT_SEEDED)
                // If currently logged in as the demo phone, reset logged in state
                if (prefs.getString(KEY_USER_PHONE, "") == "9876543210") {
                    putBoolean(KEY_IS_LOGGED_IN, false)
                    putString(KEY_USER_PHONE, "")
                    putString(KEY_USER_NAME, "")
                }
                apply()
            }
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getUserName(): String = prefs.getString(KEY_USER_NAME, "Hurifix Partner") ?: "Hurifix Partner"

    fun getUserPhone(): String = prefs.getString(KEY_USER_PHONE, "") ?: ""

    fun login(phone: String, password: String): Result<String> {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            return Result.failure(Exception("Kripya 10-digit mobile number enter karein"))
        }
        val storedPassword = prefs.getString("user_pwd_$cleanPhone", null)
        if (storedPassword == null) {
            return Result.failure(Exception("Yeh mobile number registered nahi hai. Kripya 'Create New ID' se naya account banayein."))
        }
        if (storedPassword != password) {
            return Result.failure(Exception("Galat password. Kripya sahi password enter karein."))
        }

        val name = prefs.getString("user_name_$cleanPhone", "Hurifix Partner") ?: "Hurifix Partner"
        val role = prefs.getString("user_role_$cleanPhone", "Hurifix Partner & Operations") ?: "Hurifix Partner & Operations"
        val photo = prefs.getString("user_photo_$cleanPhone", null)

        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_PHONE, cleanPhone)
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_ROLE, role)
            putString(KEY_USER_PHOTO_URI, photo)
            apply()
        }
        return Result.success(name)
    }

    fun register(name: String, phone: String, password: String): Result<String> {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.length < 10) {
            return Result.failure(Exception("Kripya 10-digit mobile number enter karein"))
        }
        if (name.isBlank()) {
            return Result.failure(Exception("Kripya apna naam enter karein"))
        }
        if (password.length < 4) {
            return Result.failure(Exception("Password kam se kam 4 aksharon ka hona chahiye"))
        }
        if (prefs.contains("user_pwd_$cleanPhone")) {
            return Result.failure(Exception("Yeh mobile number pehle se registered hai. Kripya Login karein."))
        }

        val role = "Hurifix Partner & Operations"
        prefs.edit().apply {
            putString("user_pwd_$cleanPhone", password)
            putString("user_name_$cleanPhone", name.trim())
            putString("user_role_$cleanPhone", role)
            // Auto login after sign up
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_PHONE, cleanPhone)
            putString(KEY_USER_NAME, name.trim())
            putString(KEY_USER_ROLE, role)
            remove(KEY_USER_PHOTO_URI)
            apply()
        }
        return Result.success(name.trim())
    }

    fun logout() {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, false)
            putString(KEY_USER_PHONE, "")
            putString(KEY_USER_NAME, "")
            remove(KEY_USER_ROLE)
            remove(KEY_USER_PHOTO_URI)
            apply()
        }
    }
}
