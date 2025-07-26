package com.plottwist.feature.login

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data object Error : LoginState()
}

sealed class LoginAction {
    data object ClickGoogleLogin : LoginAction()
    data class OnGoogleLoginSuccess(val idToken: String) : LoginAction()
    data class OnGoogleLoginError(val message: String) : LoginAction()
}

sealed class LoginSideEffect {
    data object NavigateToHomeScreen : LoginSideEffect()
    data object GoogleLogin : LoginSideEffect()
}
