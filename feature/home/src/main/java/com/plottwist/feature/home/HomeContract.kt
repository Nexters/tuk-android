package com.plottwist.feature.home

data class HomeState(
    val isLoggedIn: Boolean = false
)

sealed class HomeAction {
    data object ClickMyPage : HomeAction()
}

sealed class HomeSideEffect {
    data object NavigateToLoginScreen : HomeSideEffect()
}
