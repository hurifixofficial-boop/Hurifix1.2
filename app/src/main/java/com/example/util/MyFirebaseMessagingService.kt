package com.example.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Background FirebaseMessagingService to handle incoming FCM messages in real-time
 * when the app is in the background or killed.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title
            ?: remoteMessage.data["title"]
            ?: "New Notification"

        val body = remoteMessage.notification?.body
            ?: remoteMessage.data["body"]
            ?: remoteMessage.data["message"]
            ?: "You have a new update or order."

        val channelId = remoteMessage.data["channelId"] ?: "hurifix_order_alerts"

        NotificationHelper.showInstantNotification(
            context = applicationContext,
            title = title,
            message = body,
            channelId = channelId
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("MyFCMService", "Refreshed FCM token: $token")
    }
}
