package com.plottwist.feature.home

data class HomeState(
    val loginState: LoginState = LoginState.Loading
)

sealed class HomeAction {
    data object ClickMyPage : HomeAction()
    data object ClickAddGathering : HomeAction()
}

sealed class HomeSideEffect {
    data object NavigateToLoginScreen : HomeSideEffect()
    data object NavigateToCreateGatheringScreen : HomeSideEffect()
    data object NavigateToMyPageScreen : HomeSideEffect()
}

enum class LoginState {
    LoggedIn, LoggedOut, Loading
}
