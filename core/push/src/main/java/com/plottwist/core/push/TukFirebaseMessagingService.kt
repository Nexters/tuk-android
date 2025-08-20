package com.plottwist.core.push

import android.app.PendingIntent
import android.content.Intent
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.plottwist.core.notification.TukNotificationManager
import com.plottwist.core.notification.model.NotificationData
import com.plottwist.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TukFirebaseMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var tukNotificationManager: TukNotificationManager

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.data.get("title") ?: ""
        val description = message.data.get("body") ?: ""
        val deeplink = message.data.get("deepLink") ?: ""
        sendNotification(title, description, deeplink)
    }

    private fun sendNotification(title: String, description: String, deeplink: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }.apply {
            putExtra("deepLink", deeplink)
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            MainActivity.NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        tukNotificationManager.sendNotification(
            pendingIntent = pendingIntent,
            notificationData = NotificationData(
                title = title,
                description = description
            )
        )
    }


    override fun handleIntent(intent: Intent?) {
        val newIntent = intent?.apply {
            val newExtras = extras?.apply {
                remove(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)
                remove("gcm.notification.e")
            }
            replaceExtras(newExtras)
        }
        super.handleIntent(newIntent)

    }
}
