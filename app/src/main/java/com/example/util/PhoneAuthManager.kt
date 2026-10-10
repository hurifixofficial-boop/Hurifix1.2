package com.example.util

import android.app.Activity
import android.content.Context

object PhoneAuthManager {
    fun sendOtp(
        context: Context,
        activity: Activity?,
        phone: String,
        onCodeSent: (verificationId: String, codeHint: String) -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {
        val dummyVerId = "VER_ID_" + System.currentTimeMillis()
        onCodeSent(dummyVerId, "123456")
    }

    fun verifyOtp(
        context: Context,
        phone: String,
        verificationId: String,
        inputCode: String,
        onSuccess: () -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {
        if (inputCode.length == 6) {
            onSuccess()
        } else {
            onError("Invalid OTP code. Please enter 6 digits.")
        }
    }
}
