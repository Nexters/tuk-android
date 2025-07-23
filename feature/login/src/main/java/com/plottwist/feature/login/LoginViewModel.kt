package com.plottwist.feature.login

import androidx.lifecycle.ViewModel
import com.example.auth.usecase.LoginUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase

) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container = container<LoginState, LoginSideEffect>(LoginState.Idle)

    fun handleGoogleSignInRequest(task: Task<GoogleSignInAccount>?) = intent {
        reduce { LoginState.Loading }

        if (task == null) {
            return@intent
        }

        try {
            val account = task.getResult(ApiException::class.java)
            val accountId = account?.id

            if (accountId.isNullOrBlank()) {
                return@intent
            }

            val result = loginUseCase.loginWithGoogle(accountId)

            if (result.isSuccess) {
                postSideEffect(LoginSideEffect.NavigateToHomeScreen)
            }

        } catch (e: ApiException) {
            reduce { LoginState.Error }
        } catch (e: Exception) {
            reduce { LoginState.Error}
        }
    }


}