package com.plottwist.feature.mypage.notification

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class NotificationSettingViewModel @Inject constructor(
    private val application: Application
) : ContainerHost<NotificationSettingState, NotificationSettingSideEffect>, ViewModel() {

    override val container = container<NotificationSettingState, NotificationSettingSideEffect>(NotificationSettingState())

    init {
        checkNotificationStatus()
    }

    private fun checkNotificationStatus() {
        val enabled = NotificationManagerCompat
            .from(application)
            .areNotificationsEnabled()

        intent {
            reduce { state.copy(areNotificationsEnabled = enabled) }
        }
    }

    fun onClickNotificationButton() {
        intent {
            postSideEffect(NotificationSettingSideEffect.OpenNotificationSettings)
        }
    }
}