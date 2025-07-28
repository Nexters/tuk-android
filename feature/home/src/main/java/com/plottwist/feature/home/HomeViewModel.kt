package com.plottwist.feature.home

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.CheckLoginStatusUseCase
import com.plottwist.core.domain.gathering.usecase.GetGatheringsUseCase
import com.plottwist.core.domain.model.Gatherings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getGatheringsUseCase: GetGatheringsUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        observeLoginState()
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

    private fun observeLoginState() = intent {
        checkLoginStatusUseCase().map { isLoggedIn ->
            if(isLoggedIn) LoginState.LoggedIn else LoginState.LoggedOut
        }.collectLatest { loginState ->
            when(loginState) {
                LoginState.LoggedIn -> {
                    fetchGatherings(loginState)
                }

                else -> {
                    reduce { state.copy(loginState = loginState) }
                }
            }
        }
    }

    private fun fetchGatherings(loginState: LoginState) = intent {
        val result = getGatheringsUseCase()

        if(result.isSuccess) {
            reduce {
                state.copy(
                    loginState = loginState,
                    gatherings = result.getOrNull() ?: return@reduce state
                )
            }
        } else {
            reduce { state.copy(loginState = loginState) }
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
