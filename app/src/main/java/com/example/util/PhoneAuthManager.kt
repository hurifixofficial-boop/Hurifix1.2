package com.example.util

import android.app.Activity
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Phone Authentication Manager integrated with custom MacroDroid SMS Gateway & Rate Limiting.
 * Webhook: https://trigger.macrodroid.com/cac6ee5b-e2d8-4b7e-9050-5aa8f9cf8305/hurifix_otp
 */
object PhoneAuthManager {
    private const val TAG = "PhoneAuthManager"
    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * Sends OTP to the specified mobile number via MacroDroid Custom SMS Gateway.
     * Enforces rate limit of max 4 OTPs per hour per phone number.
     * Generates a random 6-digit OTP, stores locally for verification, and triggers MacroDroid Webhook.
     */
    fun sendOtp(
        context: Context,
        activity: Activity?,
        phone: String,
        onCodeSent: (verificationId: String, isFallback: Boolean) -> Unit,
        onError: (message: String) -> Unit
    ) {
        val rateLimiter = OtpRateLimiter.getInstance(context)
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")

        if (cleanPhone.length < 10) {
            onError("Please enter a valid 10-digit mobile number")
            return
        }

        GlobalLoadingManager.show("Loading...")

        // 1. Enforce Rate Limit check before dispatching
        val (canRequest, rateLimitError) = rateLimiter.checkCanRequestOtp(cleanPhone)
        if (!canRequest) {
            GlobalLoadingManager.hide()
            val errorMsg = rateLimitError ?: "Limit exceeded. Try again in an hour."
            onError(errorMsg)
            return
        }

        // 2. Generate random 6-digit OTP and store locally in state for verification
        val (_, otpCode) = rateLimiter.requestAndGenerateOtp(cleanPhone)

        // 3. Make HTTP GET request to MacroDroid Webhook Gateway
        scope.launch {
            try {
                val result = MacroDroidSmsGateway.sendOtpSms(cleanPhone, otpCode)
                result.onSuccess {
                    Log.i(TAG, "MacroDroid SMS Gateway triggered successfully for +91$cleanPhone")
                    withContext(Dispatchers.Main) {
                        GlobalLoadingManager.hide()
                        onCodeSent("MACRODROID:$otpCode", false)
                    }
                }.onFailure { exc ->
                    Log.w(TAG, "MacroDroid Gateway warning: ${exc.message}. Proceeding with local verification state.")
                    withContext(Dispatchers.Main) {
                        GlobalLoadingManager.hide()
                        onCodeSent("MACRODROID:$otpCode", true)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    GlobalLoadingManager.hide()
                    onCodeSent("MACRODROID:$otpCode", true)
                }
            }
        }
    }

    /**
     * Verifies the 6-digit OTP code provided by the user against stored local state.
     */
    fun verifyOtp(
        context: Context,
        phone: String,
        verificationId: String,
        inputCode: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ) {
        GlobalLoadingManager.show("Loading...")
        val rateLimiter = OtpRateLimiter.getInstance(context)
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        val code = inputCode.trim()

        if (code.length < 6) {
            GlobalLoadingManager.hide()
            onError("Invalid OTP")
            return
        }

        val (isValid, msg) = rateLimiter.verifyOtp(cleanPhone, code)
        GlobalLoadingManager.hide()
        if (isValid) {
            onSuccess()
        } else {
            onError(msg ?: "Invalid OTP")
        }
    }
}
