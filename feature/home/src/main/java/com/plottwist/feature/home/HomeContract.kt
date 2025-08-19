package com.plottwist.feature.home

import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.ui.UiState

data class ProposalTags(
    val whenTags: List<String> = emptyList(),
    val whereTags: List<String> = emptyList(),
    val whatTags: List<String> = emptyList()
)

data class HomeState(
    val loginState: LoginState = LoginState.Loading,
    val userName: UiState<String> = UiState.Loading,
    val gatherings: UiState<Gatherings> = UiState.Loading,
    val whenLabel: String = "",
    val whereLabel: String = "",
    val whatLabel: String = "",
    val proposalTags: UiState<ProposalTags> = UiState.Loading,
    val isRandomPlaying: Boolean = true,
    val currentIndex : Int = 0
)

sealed class HomeAction {
    data object ClickMyPage : HomeAction()
    data object ClickAddGathering : HomeAction()
    data object ClickRefreshWhere : HomeAction()
    data object ClickRefreshWhen : HomeAction()
    data object ClickRefreshWhat : HomeAction()
    data class ClickGathering(val gatheringId: Long): HomeAction()
    data class ClickPropose(val index : Int): HomeAction()
    data object ClickProposals: HomeAction()
    data object OnPermissionGranted: HomeAction()
    data object ClickPlay: HomeAction()
    data object ClickStop: HomeAction()
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
    data object ShowNoGatheringsPopup: HomeSideEffect()
    data object RequestNotificationPermission: HomeSideEffect()
    data class NavigateToSelectGatheringScreen(
        val whereLabel: String,
        val whenLabel: String,
        val whatLabel: String
    ) : HomeSideEffect()
}

enum class LoginState {
    LoggedIn, LoggedOut, Loading
}
