package com.plottwist.feature.mypage.notification

data class NotificationSettingState(
    val areNotificationsEnabled: Boolean = true
)

sealed class NotificationSettingSideEffect {
    data object OpenNotificationSettings: NotificationSettingSideEffect()
}