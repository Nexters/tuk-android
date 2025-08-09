package com.plottwist.feature.home

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.CheckLoginStatusUseCase
import com.plottwist.core.domain.gathering.usecase.GetGatheringsUseCase
import com.plottwist.core.domain.gathering.usecase.GetPurposesUseCase
import com.plottwist.core.domain.model.Purposes
import com.plottwist.core.weburl.WebUrlConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getGatheringsUseCase: GetGatheringsUseCase,
    private val getPurposesUseCase: GetPurposesUseCase,
    private val webViewConfig: WebUrlConfig,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        observeLoginState()
        getPurposes()
    }

    private fun getPurposes() = intent {
        val result = getPurposesUseCase().getOrNull() ?: Purposes()

        reduce {
            state.copy(
                whenTags = result.whenTags,
                whereTags = result.whereTags,
                whatTags = result.whatTags,
                whatLabel = result.whatTags.firstOrNull() ?: "",
                whereLabel = result.whereTags.firstOrNull() ?: "",
                whenLabel = result.whenTags.firstOrNull() ?: ""
            )
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

            HomeAction.ClickRefreshWhen -> {
                handleRefreshWhenClick()
            }

            HomeAction.ClickRefreshWhere -> {
                handleRefreshWhereClick()
            }

            HomeAction.ClickRefreshWhat -> {
                handleRefreshWhatClick()
            }

            is HomeAction.ClickGathering -> {
                handleGatheringClick(action.gatheringId)
            }

            HomeAction.ClickPropose -> {
                handleProposeClick()
            }

            HomeAction.ClickProposals -> {
                handleProposalsClick()
            }
        }
    }

    private fun observeLoginState() = intent {
        checkLoginStatusUseCase().map { isLoggedIn ->
            if (isLoggedIn) LoginState.LoggedIn else LoginState.LoggedOut
        }.distinctUntilChanged().collectLatest { loginState ->
            when (loginState) {
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

        if (result.isSuccess) {
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
        when (state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToMyPageScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleAddGatheringClick() = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToCreateGatheringScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleGatheringClick(gatheringId: Long) = intent {
        postSideEffect(HomeSideEffect.NavigateToGatheringDetailScreen(gatheringId))
    }

    private fun handleProposeClick() = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                if(state.gatherings.gatheringOverviews.isNotEmpty()) {
                    postSideEffect(
                        HomeSideEffect.NavigateToCreateProposalScreen(
                            whereLabel = state.whereLabel,
                            whenLabel = state.whenLabel,
                            whatLabel = state.whatLabel
                        )
                    )
                } else {
                    postSideEffect(HomeSideEffect.ShowNoGatheringsPopup)
                }
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleRefreshWhenClick() = intent {
        reduce {
            state.copy(
                whenLabel = state.whenTags.filterNot {
                    state.whenLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhereClick() = intent {
        reduce {
            state.copy(
                whereLabel = state.whereTags.filterNot {
                    state.whereLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhatClick() = intent {
        reduce {
            state.copy(
                whatLabel = state.whatTags.filterNot {
                    state.whatLabel == it
                }.random()
            )
        }
    }

    private fun handleProposalsClick() = intent {
        val encodedUrl = URLEncoder.encode(webViewConfig.proposalsUrl,"UTF-8")
        postSideEffect(HomeSideEffect.NavigateToWebViewScreen(encodedUrl))
    }
}
