package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.data.model.CustomerJobEntity
import com.example.data.model.ExpertEntity
import java.net.URLEncoder

object WhatsAppHelper {

    /**
     * Cleans phone number for WhatsApp URL (adds Indian country code 91 if 10-digit number).
     */
    fun formatPhoneNumberForWhatsApp(phone: String): String {
        val digitsOnly = phone.replace(Regex("[^0-9]"), "")
        return when {
            digitsOnly.length == 10 -> "91$digitsOnly"
            digitsOnly.length == 11 && digitsOnly.startsWith("0") -> "91${digitsOnly.substring(1)}"
            digitsOnly.length == 12 && digitsOnly.startsWith("91") -> digitsOnly
            else -> digitsOnly
        }
    }

    /**
     * Calculates estimated arrival time:
     * User requirement: Estimated time MUST BE 30 minutes MORE than the calculated distance time.
     */
    fun calculateEstimatedArrivalTimeWithBuffer(distanceKm: Double): String {
        val baseTravelMinutes = LocationHelper.estimateTravelTimeMinutes(distanceKm)
        val totalWithBuffer = baseTravelMinutes + 30 // +30 minutes buffer as requested
        return when {
            totalWithBuffer >= 60 -> {
                val hours = totalWithBuffer / 60
                val mins = totalWithBuffer % 60
                if (mins == 0) "$hours Hour (Approx)" else "$hours Hr $mins Mins (Approx)"
            }
            else -> "$totalWithBuffer Minutes (Approx)"
        }
    }

    /**
     * Message sent to customer informing them of the assigned expert, phone, and estimated time (+30 min buffer).
     */
    fun createCustomerAssignmentNotificationMessage(
        customerName: String,
        expertName: String,
        expertPhone: String,
        serviceType: String,
        estimatedTimeText: String
    ): String {
        val displayName = customerName.trim().ifBlank { "Customer" }
        return """
🛠 *HURIFIX SERVICE UPDATE* 🛠
━━━━━━━━━━━━━━━━━━━━
Dear *$displayName* ✨,

An expert technician has been assigned to your service request:

👨‍🔧 *Expert Name:* $expertName
📞 *Contact Number:* $expertPhone
⏳ *Estimated Time of Arrival:* $estimatedTimeText
🛠 *Service Required:* $serviceType

Our technician will arrive at your address shortly. Please keep your phone reachable.
━━━━━━━━━━━━━━━━━━━━
- *Team Hurifix*
_Many Problems | One Solution_
        """.trimIndent()
    }

    /**
     * Professional message sent to customer upon order completion:
     * Dear [Customer Name] ✨, followed by review prompt, feedback call alert, and updated Instagram link.
     */
    fun createCompletionCustomerMessage(customerName: String): String {
        val displayName = customerName.trim().ifBlank { "Customer" }
        return """
Dear $displayName ✨,

Your work has been successfully completed! 🎉 We hope we met your expectations and provided a great experience. 💯

One of our representatives will call you shortly for your valuable feedback—please do share your experience with us! 📞

If you have any questions or need assistance, feel free to reach out to us anytime. 📞💬

Stay Connected! 🚀
Follow us on Instagram for future updates, offers, and exclusive services:
👇
https://www.instagram.com/hurifix_official?stkn=MzUzMW9xOTh2eTJ4

Thank you for choosing us! Have a great day ahead! 😊🙏
        """.trimIndent()
    }

    /**
     * Builds the complete dispatch message for the expert with customer details and Google Maps location.
     */
    fun createDispatchMessage(
        expert: ExpertEntity,
        customer: CustomerJobEntity
    ): String {
        val mapsUrl = LocationHelper.createGoogleMapsUrl(customer.latitude, customer.longitude)

        return """
🔧 *HURIFIX DISPATCH ORDER* 🔧
━━━━━━━━━━━━━━━━━━━━
Hello *${expert.name}*, a new service task has been dispatched to you:

👤 *Customer Name:* ${customer.customerName}
📞 *Customer Phone:* ${customer.customerPhone}
🛠 *Service Required:* ${customer.serviceType}
📝 *Problem Description:* ${customer.issueDescription}
📍 *Address:* ${customer.address}

🗺 *Google Maps Location:*
$mapsUrl
━━━━━━━━━━━━━━━━━━━━
- *Hurifix Operations Team*
        """.trimIndent()
    }

    /**
     * Opens WhatsApp directly with a custom message.
     */
    fun sendWhatsAppDirectMessage(
        context: Context,
        phoneNumber: String,
        message: String
    ) {
        val formattedNumber = formatPhoneNumberForWhatsApp(phoneNumber)
        val encodedMessage = try {
            URLEncoder.encode(message, "UTF-8")
        } catch (e: Exception) {
            message
        }

        val url = "https://api.whatsapp.com/send?phone=$formattedNumber&text=$encodedMessage"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(fallbackIntent)
            } catch (e2: Exception) {
                Toast.makeText(context, "WhatsApp is not installed. Text copied to clipboard!", Toast.LENGTH_LONG).show()
                copyToClipboard(context, "Hurifix Message", message)
            }
        }
    }

    /**
     * Opens WhatsApp directly with the pre-filled dispatch message for the target expert.
     */
    fun sendWhatsAppMessageToExpert(
        context: Context,
        expert: ExpertEntity,
        customer: CustomerJobEntity
    ) {
        val message = createDispatchMessage(expert, customer)
        sendWhatsAppDirectMessage(context, expert.phone, message)
    }

    /**
     * Cleans phone number to strictly digits for system dialer (removes all brackets, dashes, spaces, etc.).
     * Prefixes with country code +91 for 10-digit Indian numbers so the Android dialer app recognizes it as
     * an Indian number and NEVER auto-formats it with North American parentheses/brackets like (987) 654-3210.
     */
    fun sanitizePhoneNumberForDialer(phone: String): String {
        val digitsOnly = phone.filter { it.isDigit() }
        val clean10 = when {
            digitsOnly.length == 10 -> digitsOnly
            digitsOnly.length == 12 && digitsOnly.startsWith("91") -> digitsOnly.substring(2)
            digitsOnly.length == 11 && digitsOnly.startsWith("0") -> digitsOnly.substring(1)
            else -> digitsOnly
        }
        return if (clean10.length == 10) "+91$clean10" else if (digitsOnly.startsWith("91")) "+$digitsOnly" else "+91$digitsOnly"
    }

    /**
     * Launches the phone dialer with clean digits only without brackets.
     * Also copies the pure 10-digit number to clipboard for user convenience.
     */
    fun openDialer(context: Context, phone: String) {
        val finalDialerUri = sanitizePhoneNumberForDialer(phone)
        val digitsOnly = phone.filter { it.isDigit() }
        val clean10 = if (digitsOnly.length == 10) digitsOnly else digitsOnly.takeLast(10)

        try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Phone Number", clean10)
            clipboard.setPrimaryClip(clip)
        } catch (_: Exception) {}

        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$finalDialerUri")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open dialer: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Opens WhatsApp chat directly with target number WITHOUT any pre-filled message.
     * (As explicitly requested for Manage Experts WhatsApp button).
     */
    fun openWhatsAppChatWithoutMessage(context: Context, phoneNumber: String) {
        val formattedNumber = formatPhoneNumberForWhatsApp(phoneNumber)
        val url = "https://api.whatsapp.com/send?phone=$formattedNumber"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            try {
                val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$formattedNumber")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(fallbackIntent)
            } catch (e2: Exception) {
                Toast.makeText(context, "Could not open WhatsApp: ${e2.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Opens Google Maps navigation to coordinates.
     */
    fun openGoogleMaps(context: Context, latitude: Double, longitude: Double, label: String = "Location") {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
            setPackage("com.google.android.apps.maps")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(LocationHelper.createGoogleMapsUrl(latitude, longitude))).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(webIntent)
        }
    }

    /**
     * Welcome message for newly registered Hurifix Expert.
     */
    fun createNewExpertWelcomeMessage(expertName: String): String {
        return """
Hello $expertName, 👋
Welcome to Hurifix! 🎉
Aap ab hamare Official Business Partner ban chuke hain. Hum aapke sath kaam karne ke liye bohot excited hain! 🛠️🚀
Hum aapko jald hi WhatsApp par ek message bhejenge jisme aapko hamara poora work process aur guidelines achhe se samjha di jayengi. 📲
Hurifix family se judne ke liye aapka bohot dhanyawad! Have a great day! 😊🙏
        """.trimIndent()
    }

    /**
     * Copies text to system clipboard.
     */
    fun copyToClipboard(context: Context, label: String, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
    }
}
