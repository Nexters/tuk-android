package com.plottwist.feature.mypage

import com.plottwist.core.domain.model.GatheringMember

data class MyPageState(
    val gatheringUser: GatheringMember = GatheringMember(),
    val appVersionName: String = ""
)

sealed class MyPageAction {
    data object ClickEditName : MyPageAction()
    data object ClickNotificationSetting : MyPageAction()
    data object ClickUpdateApp : MyPageAction()
    data object ClickTerms : MyPageAction()
    data object ClickPrivacyPolicy : MyPageAction()
    data object ClickLogout : MyPageAction()
    data object ClickDeleteAccount : MyPageAction()
}

sealed class MyPageSideEffect {
    data object NavigateToEditName : MyPageSideEffect()
    data object NavigateToNotificationSetting: MyPageSideEffect()
    data object NavigateToTerms: MyPageSideEffect()
    data object NavigateToPrivacyPolicy:MyPageSideEffect()
    data object NavigateToHome: MyPageSideEffect()
    data object NavigateToUpdateApp: MyPageSideEffect()
}
