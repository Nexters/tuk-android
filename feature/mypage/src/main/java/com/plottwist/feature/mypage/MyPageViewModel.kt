package com.plottwist.feature.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(

) :ContainerHost<MyPageState, MyPageSideEffect>,ViewModel(){

    override val container = container<MyPageState, MyPageSideEffect>(
        MyPageState()
    )

    fun onAction(action: MyPageAction) = intent {
        when (action) {
            MyPageAction.ClickEditName -> postSideEffect(MyPageSideEffect.NavigateToEditName)
            MyPageAction.ClickNotificationSetting -> postSideEffect(MyPageSideEffect.NavigateToNotificationSetting)
            MyPageAction.ClickUpdateApp -> postSideEffect(MyPageSideEffect.NavigateToUpdateApp)
            MyPageAction.ClickTerms -> postSideEffect(MyPageSideEffect.NavigateToTerms)
            MyPageAction.ClickPrivacyPolicy -> postSideEffect(MyPageSideEffect.NavigateToPrivacyPolicy)
            MyPageAction.ClickLogout -> postSideEffect(MyPageSideEffect.NavigateToLogout)
        }
    }
}