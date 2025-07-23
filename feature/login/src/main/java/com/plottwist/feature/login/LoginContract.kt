package com.plottwist.feature.login

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data object Error : LoginState()
}

sealed class LoginSideEffect {
    data object NavigateToHomeScreen : LoginSideEffect()
    data object NavigateToMyPage : LoginSideEffect()
}
