package com.example.ui.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.util.PhoneAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthUiState(
    val phone: String = "",
    val isSendingOtp: Boolean = false,
    val isOtpSent: Boolean = false,
    val activeVerificationId: String = "",
    val activeOtpCodeHint: String = "",
    val resendTimerSeconds: Int = 0
)

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun onPhoneChanged(phone: String) {
        _uiState.value = _uiState.value.copy(phone = phone)
    }

    fun verifyPhoneRegistrationAndSendOtp(
        context: Context,
        activity: Activity?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        _uiState.value = _uiState.value.copy(isSendingOtp = true)
        PhoneAuthManager.sendOtp(
            context = context,
            activity = activity,
            phone = _uiState.value.phone,
            onCodeSent = { verificationId, _ ->
                _uiState.value = _uiState.value.copy(
                    isSendingOtp = false,
                    isOtpSent = true,
                    activeVerificationId = verificationId,
                    resendTimerSeconds = 60
                )
                onSuccess()
            },
            onError = { err ->
                _uiState.value = _uiState.value.copy(isSendingOtp = false)
                onError(err)
            }
        )
    }
}
