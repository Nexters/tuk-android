package com.plottwist.feature.home

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.CheckLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        observeLoginState()
    }

    private fun observeLoginState() = intent {
        checkLoginStatusUseCase().map { isLoggedIn ->
            if(isLoggedIn) LoginState.LoggedIn else LoginState.LoggedOut
        }.collectLatest {
            reduce { state.copy(loginState = it) }
        }
    }

    fun handleAction(action: HomeAction) {
        when (action) {
            HomeAction.ClickMyPage -> {
                handleMyPageClick()
            }

            HomeAction.ClickAddGathering -> {
                handleAddGatheringClick()
            }
        }
    }

    private fun handleMyPageClick() = intent {
        when(state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToMyPageScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleAddGatheringClick() = intent {
        when(state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToCreateGatheringScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }
}
