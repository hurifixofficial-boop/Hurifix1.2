package com.example.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HurifixConfirmDialog(
    title: String,
    message: String,
    confirmText: String = "Confirm",
    dismissText: String = "Cancel",
    icon: ImageVector? = null,
    isDestructive: Boolean = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    HurifixConfirmDialogKt.HurifixConfirmDialog(
        title,
        message,
        confirmText,
        dismissText,
        icon,
        isDestructive,
        onConfirm,
        onDismiss
    )
}

@Composable
fun OtpVerificationDialog(
    phone: String,
    purposeTitle: String = "Verify OTP Code",
    purposeSubtitle: String = "",
    onSuccess: () -> Unit,
    onDismiss: () -> Unit
) {
    OtpVerificationDialogKt.OtpVerificationDialog(
        phone,
        purposeTitle,
        purposeSubtitle,
        onSuccess,
        onDismiss
    )
}

@Composable
fun ScrollToTopButton(
    visible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollToTopButtonKt.ScrollToTopButton(
        visible,
        onClick,
        modifier
    )
}
