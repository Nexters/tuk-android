package com.plottwist.core.notification.model

data class NotificationData(
    val title: String,
    val description: String,
    val deeplink: String = ""
)
