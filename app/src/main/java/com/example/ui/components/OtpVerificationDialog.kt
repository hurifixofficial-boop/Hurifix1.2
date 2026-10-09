package com.example.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.util.OtpRateLimiter
import com.example.util.PhoneAuthManager
import com.example.util.SoundHelper
import kotlinx.coroutines.delay

@Composable
fun OtpVerificationDialog(
    phone: String,
    purposeTitle: String = "Mobile OTP Verification",
    purposeSubtitle: String = "Verify mobile number to proceed",
    onSuccess: () -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    val rateLimiter = remember { OtpRateLimiter.getInstance(context) }
    val cleanPhone = remember(phone) { phone.replace(Regex("[^0-9]"), "") }

    var otpInput by remember { mutableStateOf("") }
    var activeVerificationId by remember { mutableStateOf("") }
    var activeOtpCodeHint by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isVerifying by remember { mutableStateOf(false) }
    var isSendingOtp by remember { mutableStateOf(false) }
    var isVerifiedSuccess by remember { mutableStateOf(false) }

    // 60 second resend timer
    var resendTimerSeconds by remember { mutableIntStateOf(60) }

    fun performVerifyOtp() {
        if (otpInput.length < 6) {
            return
        }

        isVerifying = true
        PhoneAuthManager.verifyOtp(
            context = context,
            phone = cleanPhone,
            verificationId = activeVerificationId.ifBlank { "FALLBACK:${activeOtpCodeHint}" },
            inputCode = otpInput,
            onSuccess = {
                isVerifying = false
                isVerifiedSuccess = true
                SoundHelper.playSFX("success")
            },
            onError = { _ ->
                isVerifying = false
            }
        )
    }

    fun sendOtpCode() {
        isSendingOtp = true
        errorMessage = null

        // Rate limit check first
        val (canReq, _) = rateLimiter.checkCanRequestOtp(cleanPhone)
        if (!canReq) {
            isSendingOtp = false
            return
        }

        PhoneAuthManager.sendOtp(
            context = context,
            activity = activity,
            phone = cleanPhone,
            onCodeSent = { verId, _ ->
                isSendingOtp = false
                activeVerificationId = verId
                resendTimerSeconds = 60
                Toast.makeText(context, "OTP Sent", Toast.LENGTH_SHORT).show()
            },
            onError = { _ ->
                isSendingOtp = false
            }
        )
    }

    // Initial OTP Request on Dialog Open
    LaunchedEffect(cleanPhone) {
        sendOtpCode()
    }

    // Timer Countdown Loop
    LaunchedEffect(resendTimerSeconds) {
        if (resendTimerSeconds > 0) {
            delay(1000L)
            resendTimerSeconds -= 1
        }
    }

    // Success Animation Delayed Dismiss
    LaunchedEffect(isVerifiedSuccess) {
        if (isVerifiedSuccess) {
            delay(900L)
            onSuccess()
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        Icons.Default.MarkEmailRead,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Column {
                    Text(
                        text = purposeTitle,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = purposeSubtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        text = {
            if (isVerifiedSuccess) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedSuccessCheckmark(size = 68.dp)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "✅ Mobile OTP Verified!",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Hidden Container for invisible Recaptcha as specified in Requirement 1
                    Box(
                        modifier = Modifier
                            .size(1.dp)
                            .alpha(0f)
                            .testTag("invisible_recaptcha_container")
                    )

                    // Phone Badge
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.primary)
                                Text(
                                    text = "📱 +91 $cleanPhone",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "SMS Verification",
                                    fontSize = 10.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }

                    // 6-Digit OTP Entry Field
                    OutlinedTextField(
                        value = otpInput,
                        onValueChange = { input ->
                            otpInput = input.filter { it.isDigit() }.take(6)
                        },
                        label = { Text("Enter 6-Digit OTP Code") },
                        placeholder = { Text("123456") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            textAlign = TextAlign.Center,
                            letterSpacing = 4.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("otp_input_field")
                    )

                    // Resend Timer & Button Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (resendTimerSeconds > 0) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Timer, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = "Resend code in ${resendTimerSeconds}s",
                                    fontSize = 11.5.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        } else {
                            TextButton(
                                onClick = {
                                    sendOtpCode()
                                },
                                enabled = !isSendingOtp
                            ) {
                                if (isSendingOtp) {
                                    CircularProgressIndicator(modifier = Modifier.size(12.dp), strokeWidth = 1.5.dp)
                                    Spacer(Modifier.width(4.dp))
                                    Text("Sending...", fontSize = 12.sp)
                                } else {
                                    Text("🔄 Resend OTP Code", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Text(
                            text = "Max 4 OTPs/hr",
                            fontSize = 10.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        },
        confirmButton = {
            if (!isVerifiedSuccess) {
                Button(
                    onClick = { performVerifyOtp() },
                    enabled = !isVerifying && otpInput.length == 6,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isVerifying) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(Modifier.width(6.dp))
                        Text("Verifying...")
                    } else {
                        Text("Verify OTP & Proceed", fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                Box(Modifier.size(0.dp))
            }
        },
        dismissButton = {
            if (!isVerifiedSuccess) {
                OutlinedButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Cancel")
                }
            } else {
                Box(Modifier.size(0.dp))
            }
        }
    )
}
