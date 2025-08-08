package com.plottwist.feature.home

import com.plottwist.core.domain.model.Gatherings

data class HomeState(
    val loginState: LoginState = LoginState.Loading,
    val gatherings: Gatherings = Gatherings(),
    val whenLabel: String = "",
    val whereLabel: String = "",
    val whatLabel: String = "",
    val whenTags: List<String> = emptyList(),
    val whereTags: List<String> = emptyList(),
    val whatTags: List<String> = emptyList()
)

sealed class HomeAction {
    data object ClickMyPage : HomeAction()
    data object ClickAddGathering : HomeAction()
    data object ClickRefreshWhere : HomeAction()
    data object ClickRefreshWhen : HomeAction()
    data object ClickRefreshWhat : HomeAction()
    data class ClickGathering(val gatheringId: Long): HomeAction()
    data object ClickPropose: HomeAction()
    data object ClickProposals: HomeAction()
}

sealed class HomeSideEffect {
    data object NavigateToLoginScreen : HomeSideEffect()
    data object NavigateToCreateGatheringScreen : HomeSideEffect()
    data object NavigateToMyPageScreen : HomeSideEffect()
    data class NavigateToGatheringDetailScreen(
        val gatheringId: Long
    ) : HomeSideEffect()
    data class NavigateToCreateProposalScreen(
        val whereLabel: String,
        val whenLabel: String,
        val whatLabel: String
    ) : HomeSideEffect()
    data class NavigateToWebViewScreen(
        val encodedUrl: String
    ) : HomeSideEffect()
}

enum class LoginState {
    LoggedIn, LoggedOut, Loading
}
