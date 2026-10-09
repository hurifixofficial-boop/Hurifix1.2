package com.example.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.data.model.CustomerJobEntity

object NotificationHelper {
    private const val CHANNEL_ID_ORDERS = "hurifix_orders_channel"

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID_ORDERS,
                "Order Alerts",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for Hurifix orders and assignments"
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun checkAndTriggerReminders(context: Context, jobs: List<CustomerJobEntity>) {
        // No-op or trigger notifications as needed
    }

    fun showNotification(context: Context, id: Int, title: String, message: String) {
        try {
            val builder = NotificationCompat.Builder(context, CHANNEL_ID_ORDERS)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            val manager = NotificationManagerCompat.from(context)
            if (LocationHelper.isLocationPermissionGranted(context)) {
                manager.notify(id, builder.build())
            }
        } catch (_: SecurityException) {
        } catch (_: Exception) {
        }
    }
}
