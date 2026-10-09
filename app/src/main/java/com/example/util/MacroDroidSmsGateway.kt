package com.example.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

/**
 * MacroDroid Custom SMS Gateway Integration
 * Webhook URL: https://trigger.macrodroid.com/cac6ee5b-e2d8-4b7e-9050-5aa8f9cf8305/hurifix_otp
 * Query Parameters:
 *  - phone: User's entered phone number
 *  - message: "Hurifix Services: Your OTP code is [OTP].Hurifix-Many Problems One Solution"
 */
object MacroDroidSmsGateway {
    private const val TAG = "MacroDroidSmsGateway"
    private const val BASE_WEBHOOK_URL = "https://trigger.macrodroid.com/cac6ee5b-e2d8-4b7e-9050-5aa8f9cf8305/hurifix_otp"

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Sends an HTTP GET request to MacroDroid Webhook to dispatch SMS to the user's mobile number.
     */
    suspend fun sendOtpSms(phone: String, otpCode: String): Result<Unit> = withContext(Dispatchers.IO) {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        val smsMessage = "Hurifix Services: Your OTP code is $otpCode.Hurifix-Many Problems One Solution"

        val encodedPhone = URLEncoder.encode(cleanPhone, "UTF-8")
        val encodedMessage = URLEncoder.encode(smsMessage, "UTF-8")
        val fullUrl = "$BASE_WEBHOOK_URL?phone=$encodedPhone&message=$encodedMessage"

        val request = try {
            Request.Builder()
                .url(fullUrl)
                .get()
                .build()
        } catch (e: Exception) {
            Log.e(TAG, "Error building MacroDroid request: ${e.message}", e)
            return@withContext Result.failure(e)
        }

        try {
            val response = client.newCall(request).execute()
            response.use { resp ->
                val responseCode = resp.code
                if (resp.isSuccessful) {
                    Log.i(TAG, "✅ MacroDroid SMS Gateway triggered successfully for phone: $cleanPhone")
                    Result.success(Unit)
                } else {
                    val errorBody = resp.body?.string() ?: ""
                    Log.e(TAG, "❌ MacroDroid Webhook HTTP $responseCode: $errorBody")
                    Result.failure(Exception("MacroDroid Gateway HTTP $responseCode"))
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ Exception connecting to MacroDroid Webhook: ${e.message}", e)
            Result.failure(e)
        }
    }
}
