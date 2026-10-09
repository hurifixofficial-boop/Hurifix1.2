package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    fun openWhatsAppChatWithoutMessage(context: Context, phone: String) {
        val normalized = normalizePhoneNumber(phone)
        try {
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$normalized")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
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

    fun sendWhatsAppDirectMessage(context: Context, phone: String, message: String) {
        openWhatsAppChat(context, phone, message)
    }

    fun openGoogleMaps(context: Context, latitude: Double, longitude: Double, label: String = "") {
        try {
            val uri = if (label.isNotBlank()) {
                Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude(${Uri.encode(label)})")
            } else {
                Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
            }
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
                val webIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(webIntent)
            } catch (e2: Exception) {
                Toast.makeText(context, "Could not open Maps: ${e2.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun calculateEstimatedArrivalTimeWithBuffer(distanceKm: Double): String {
        val travelMinutes = (3.5 * distanceKm).toInt().coerceAtLeast(10) + 30
        val targetTime = System.currentTimeMillis() + (travelMinutes * 60 * 1000L)
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(Date(targetTime))
    }

    fun sendWhatsAppMessageToExpert(context: Context, expert: ExpertEntity, customer: CustomerJobEntity) {
        val sb = StringBuilder()
        sb.append("🔔 *New Job Assignment - Hurifix*\n\n")
        sb.append("👤 *Customer:* ${customer.customerName}\n")
        sb.append("📞 *Phone:* ${customer.customerPhone}\n")
        sb.append("🛠️ *Service:* ${customer.serviceType}\n")
        if (customer.issueDescription.isNotBlank()) {
            sb.append("📝 *Issue:* ${customer.issueDescription}\n")
        }
        if (customer.address.isNotBlank()) {
            sb.append("📍 *Address:* ${customer.address}\n")
        }
        if (customer.latitude != 0.0 && customer.longitude != 0.0) {
            sb.append("🗺️ *Location:* https://maps.google.com/?q=${customer.latitude},${customer.longitude}\n")
        }
        sb.append("\nPlease confirm and proceed as soon as possible!")
        openWhatsAppChat(context, expert.phone, sb.toString())
    }

    fun createCustomerAssignmentNotificationMessage(
        customerName: String,
        expertName: String,
        expertPhone: String,
        serviceType: String = "service",
        estimatedTimeText: String = "soon"
    ): String {
        return "Hello $customerName! Your request for $serviceType has been assigned to expert $expertName ($expertPhone). Expected arrival: $estimatedTimeText."
    }

    fun createCompletionCustomerMessage(
        customerName: String,
        serviceType: String = "service",
        expertName: String = "Hurifix Expert"
    ): String {
        return "Hello $customerName! Your $serviceType service by $expertName is completed. Thank you for choosing Hurifix!"
    }

    fun createNewExpertWelcomeMessage(expertName: String, category: String = "Professional"): String {
        return "Welcome $expertName to Hurifix as a verified $category expert! We look forward to working with you."
    }
}
