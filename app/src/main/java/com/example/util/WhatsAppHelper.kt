package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity

object WhatsAppHelper {
    fun normalizePhoneNumber(phone: String): String {
        val digits = phone.filter { it.isDigit() }
        return when {
            digits.length == 10 -> "91$digits"
            digits.length == 11 && digits.startsWith("0") -> "91${digits.substring(1)}"
            digits.length == 12 && digits.startsWith("91") -> digits
            else -> digits
        }
    }

    fun openWhatsAppChat(context: Context, phone: String, message: String) {
        val normalized = normalizePhoneNumber(phone)
        try {
            val encodedMsg = Uri.encode(message)
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$normalized&text=$encodedMsg")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsAppChatWithoutMessage(context: Context, phone: String) {
        openWhatsAppChat(context, phone, "Hello")
    }

    fun sendWhatsAppDirectMessage(context: Context, phone: String, message: String) {
        openWhatsAppChat(context, phone, message)
    }

    fun openDialer(context: Context, phone: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open dialer: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendWhatsAppMessageToExpert(context: Context, expert: ExpertEntity, customer: CustomerJobEntity) {
        val msg = "Hello ${expert.name},\nNew Job assigned:\nCustomer: ${customer.customerName}\nPhone: ${customer.customerPhone}\nService: ${customer.serviceType}\nAddress: ${customer.address}"
        openWhatsAppChat(context, expert.phone, msg)
    }

    fun calculateEstimatedArrivalTimeWithBuffer(distanceKm: Double): String {
        val minutes = (distanceKm * 3).toInt().coerceAtLeast(15)
        return "$minutes mins"
    }

    fun createCustomerAssignmentNotificationMessage(
        customerName: String,
        expertName: String,
        expertPhone: String,
        serviceType: String,
        estimatedTimeText: String
    ): String {
        return "Hello $customerName,\nYour technician $expertName ($expertPhone) has been assigned for $serviceType. Estimated arrival: $estimatedTimeText."
    }

    fun createCompletionCustomerMessage(customerName: String): String {
        return "Hello $customerName,\nThank you for using Hurifix! Your service is completed and covered under 10-day warranty."
    }

    fun createNewExpertWelcomeMessage(expertName: String, category: String): String {
        return "Welcome to Hurifix, $expertName! You are registered as a $category expert."
    }
}
