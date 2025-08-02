package com.plottwist.feature.home

import com.plottwist.core.domain.model.Gatherings

data class HomeState(
    val loginState: LoginState = LoginState.Loading,
    val gatherings: Gatherings = Gatherings(),
    val whenLabel: String = HomeViewModel.whenLabels.firstOrNull() ?: "",
    val whereLabel: String = HomeViewModel.whereLabels.firstOrNull() ?: "",
    val whatLabel: String = HomeViewModel.whatLabels.firstOrNull() ?: "",
)

sealed class HomeAction {
    data object ClickMyPage : HomeAction()
    data object ClickAddGathering : HomeAction()
    data object ClickRefreshWhere : HomeAction()
    data object ClickRefreshWhen : HomeAction()
    data object ClickRefreshWhat : HomeAction()
    data class ClickGathering(val gatheringId: Long): HomeAction()
}

sealed class HomeSideEffect {
    data object NavigateToLoginScreen : HomeSideEffect()
    data object NavigateToCreateGatheringScreen : HomeSideEffect()
    data object NavigateToMyPageScreen : HomeSideEffect()
    data class NavigateToGatheringDetailScreen(
        val gatheringId: Long
    ) : HomeSideEffect()
}

enum class LoginState {
    LoggedIn, LoggedOut, Loading
}
