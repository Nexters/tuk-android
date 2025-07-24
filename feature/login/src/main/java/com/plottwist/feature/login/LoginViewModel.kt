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

    fun handleGoogleSignInRequest(idToken: String) = intent {
        reduce { LoginState.Loading }

        try {
            val result = loginUseCase.loginWithGoogle(idToken)

            if (result.isSuccess) {
                postSideEffect(LoginSideEffect.NavigateToHomeScreen)
            }

        } catch (e: Exception) {
            reduce { LoginState.Error}
        }
    }


}
