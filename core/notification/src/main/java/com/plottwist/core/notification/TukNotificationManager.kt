package com.plottwist.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.plottwist.core.notification.model.NotificationData
import javax.inject.Inject
import com.plottwist.core.designsystem.R

class TukNotificationManager @Inject constructor(
    private val context: Context
) {
    fun sendNotification(
        pendingIntent: PendingIntent,
        notificationData: NotificationData
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = TUK_CHANNEL_ID

        val channel = NotificationChannel(
            channelId,
            TUK_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationData.title)
            .setContentText(notificationData.description)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notificationManager.notify(TUK_NOTIFICATION_DEFAULT_ID, notificationBuilder.build())
    }

    companion object {
        const val DEFAULT_TITLE = "TUK"
        const val DEFAULT_DESCRIPTION = "TUK"
    }
}
