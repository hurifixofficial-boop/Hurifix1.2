package com.example.util

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.util.concurrent.ConcurrentHashMap

/**
 * OTP Rate Limiter & Code Generator
 * Rule: Maximum 4 OTP requests per hour (60 minutes) per phone number.
 * Persists request timestamps in SharedPreferences for crash/restart safety.
 */
class OtpRateLimiter private constructor(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "hurifix_otp_rate_limit"
        private const val MAX_REQUESTS_PER_HOUR = 4
        private const val ONE_HOUR_MS = 60 * 60 * 1000L // 60 minutes

        @Volatile
        private var instance: OtpRateLimiter? = null

        fun getInstance(context: Context): OtpRateLimiter {
            return instance ?: synchronized(this) {
                instance ?: OtpRateLimiter(context.applicationContext).also { instance = it }
            }
        }
    }

    // Active in-memory generated OTP codes for active verification sessions
    private val activeOtpCodes = ConcurrentHashMap<String, String>()

    /**
     * Retrieves the active generated OTP code for a phone number if present.
     */
    fun getActiveOtp(phone: String): String? {
        val clean = phone.replace(Regex("[^0-9]"), "")
        return activeOtpCodes[clean]
    }

    /**
     * Checks if an OTP can be requested for the given phone number.
     * Returns Pair(allowed: Boolean, errorMessage: String?)
     */
    fun checkCanRequestOtp(phone: String): Pair<Boolean, String?> {
        val clean = phone.replace(Regex("[^0-9]"), "")
        if (clean.length < 10) {
            return Pair(false, "Please enter a valid 10-digit mobile number")
        }

        val now = System.currentTimeMillis()
        val timestamps = getTimestampsForPhone(clean)
        val validTimestamps = timestamps.filter { now - it < ONE_HOUR_MS }

        if (validTimestamps.size >= MAX_REQUESTS_PER_HOUR) {
            return Pair(false, "Limit exceeded. Try again in an hour.")
        }

        return Pair(true, null)
    }

    /**
     * Records a new OTP request for the given phone number and returns the generated 6-digit OTP code.
     */
    fun requestAndGenerateOtp(phone: String): Pair<Boolean, String> {
        val clean = phone.replace(Regex("[^0-9]"), "")
        val (allowed, error) = checkCanRequestOtp(clean)
        if (!allowed) {
            return Pair(false, error ?: "Limit exceeded. Try again in an hour.")
        }

        val now = System.currentTimeMillis()
        val timestamps = getTimestampsForPhone(clean).filter { now - it < ONE_HOUR_MS }.toMutableList()
        timestamps.add(now)

        saveTimestampsForPhone(clean, timestamps)

        // Generate 6-digit OTP code
        val generatedCode = (100000..999999).random().toString()
        activeOtpCodes[clean] = generatedCode

        Log.d("OtpRateLimiter", "OTP Generated for $clean: $generatedCode (Requests in last hour: ${timestamps.size})")
        return Pair(true, generatedCode)
    }

    /**
     * Verifies if the provided OTP code matches the active code for the phone number.
     */
    fun verifyOtp(phone: String, inputCode: String): Pair<Boolean, String?> {
        val clean = phone.replace(Regex("[^0-9]"), "")
        val active = activeOtpCodes[clean]

        if (active == null) {
            return Pair(false, "OTP expired or not requested. Please request a new OTP.")
        }

        if (inputCode.trim() == active.trim()) {
            activeOtpCodes.remove(clean)
            return Pair(true, "OTP verified successfully!")
        }

        return Pair(false, "Invalid OTP code. Please enter the correct 6-digit code.")
    }

    /**
     * Resends an OTP for the phone number if within rate limit limits.
     */
    fun resendOtp(phone: String): Pair<Boolean, String> {
        return requestAndGenerateOtp(phone)
    }

    private fun getTimestampsForPhone(phone: String): List<Long> {
        val jsonStr = prefs.getString("history_$phone", null) ?: return emptyList()
        val list = mutableListOf<Long>()
        try {
            val array = JSONArray(jsonStr)
            for (i in 0 until array.length()) {
                list.add(array.getLong(i))
            }
        } catch (_: Exception) {}
        return list
    }

    private fun saveTimestampsForPhone(phone: String, timestamps: List<Long>) {
        try {
            val array = JSONArray()
            timestamps.forEach { array.put(it) }
            prefs.edit().putString("history_$phone", array.toString()).apply()
        } catch (_: Exception) {}
    }
}
