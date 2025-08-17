package com.plottwist.feature.login

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container = container<LoginState, LoginSideEffect>(LoginState.Idle)

    fun handleAction(action: LoginAction) {
        when(action) {
            LoginAction.ClickGoogleLogin -> {
                clickGoogleLogin()
            }

            is LoginAction.OnGoogleLoginSuccess -> {
                login(action.idToken)
            }

            is LoginAction.OnGoogleLoginError -> {
                // TODO 에러처리
            }
        }
    }

    private fun clickGoogleLogin() = intent {
        postSideEffect(LoginSideEffect.GoogleLogin)
    }

    private fun login(idToken: String) = intent {
        reduce { LoginState.Loading }

        try {
            val completedOnboarding = loginUseCase.loginWithGoogle(idToken).getOrDefault(false)

            if (completedOnboarding) {
                postSideEffect(LoginSideEffect.NavigateToHomeScreen)
            } else {
                postSideEffect(LoginSideEffect.NavigateToOnboardingScreen)
            }
        } catch (e: Exception) {
            reduce { LoginState.Error}
        }
    }
}
