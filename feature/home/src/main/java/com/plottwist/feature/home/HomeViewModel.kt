package com.plottwist.feature.home

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.CheckLoginStatusUseCase
import com.plottwist.core.domain.gathering.usecase.GetGatheringsUseCase
import com.plottwist.core.domain.gathering.usecase.GetPurposesUseCase
import com.plottwist.core.domain.model.Purposes
import com.plottwist.core.domain.onboarding.usecase.GetMemberInfoUseCase
import com.plottwist.core.domain.onboarding.usecase.UpdateDeviceTokenUseCase
import com.plottwist.core.ui.UiState
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
    private val updateDeviceTokenUseCase: UpdateDeviceTokenUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        observeLoginState()
        getPurposes()
        getMemberInfo()
    }

    private fun getPurposes() = intent {
        getPurposesUseCase().onSuccess { result ->
            reduce {
                state.copy(
                    proposalTags = UiState.Success(
                        ProposalTags(
                            whenTags = result.whenTags,
                            whereTags = result.whereTags,
                            whatTags = result.whatTags
                        )
                    ),
                    whatLabel = result.whatTags.firstOrNull() ?: "",
                    whereLabel = result.whereTags.firstOrNull() ?: "",
                    whenLabel = result.whenTags.firstOrNull() ?: ""
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    proposalTags = UiState.Error,
                )
            }
        }

    }

    private fun getMemberInfo() = intent {
        getMemberInfoUseCase().onSuccess { result ->
            reduce {
                state.copy(
                    userName = UiState.Success(result.name)
                )
            }
        }.onFailure {
            reduce {
                state.copy(
                    userName = UiState.Error
                )
            }
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

            is HomeAction.ClickPropose -> {
                handleProposeClick(action.index)
            }

            HomeAction.ClickProposals -> {
                handleProposalsClick()
            }

            HomeAction.OnPermissionGranted -> {
                handleOnPermissionGranted()
            }
        }
    }

    private fun observeLoginState() = intent {
        checkLoginStatusUseCase().map { isLoggedIn ->
            if (isLoggedIn) LoginState.LoggedIn else LoginState.LoggedOut
        }.distinctUntilChanged().collectLatest { loginState ->
            when (loginState) {
                LoginState.LoggedIn -> {
                    getMemberInfo()
                    fetchGatherings(loginState)
                    updateDeviceTokenUseCase()
                    postSideEffect(HomeSideEffect.RequestNotificationPermission)
                }

                else -> {
                    reduce {
                        state.copy(
                            loginState = loginState,
                            gatherings = UiState.Error
                        )
                    }
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
                    gatherings = UiState.Success(result.getOrNull() ?: return@reduce state)
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

    private fun handleProposeClick(index: Int) = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                if (state.gatherings !is UiState.Success && state.proposalTags !is UiState.Success) return@intent
                val gatherings = state.gatherings as UiState.Success
                val proposalTags = state.proposalTags as UiState.Success
                if (gatherings.value.gatheringOverviews.isNotEmpty()) {
                    postSideEffect(
                        HomeSideEffect.NavigateToSelectGatheringScreen(
                            whereLabel = proposalTags.value.whereTags[index],
                            whenLabel = proposalTags.value.whenTags[index],
                            whatLabel = proposalTags.value.whatTags[index]
                        )
                    )
                } else {
                    postSideEffect(
                        HomeSideEffect.NavigateToCreateProposalScreen(
                            whereLabel = proposalTags.value.whereTags[index],
                            whenLabel = proposalTags.value.whenTags[index],
                            whatLabel = proposalTags.value.whatTags[index]
                        )
                    )
                }
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleRefreshWhenClick() = intent {
        if (state.proposalTags !is UiState.Success) return@intent
        val proposalTags = state.proposalTags as UiState.Success
        reduce {
            state.copy(
                whenLabel = proposalTags.value.whenTags.filterNot {
                    state.whenLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhereClick() = intent {
        if (state.proposalTags !is UiState.Success) return@intent
        val proposalTags = state.proposalTags as UiState.Success
        reduce {
            state.copy(
                whereLabel = proposalTags.value.whereTags.filterNot {
                    state.whereLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhatClick() = intent {
        if (state.proposalTags !is UiState.Success) return@intent
        val proposalTags = state.proposalTags as UiState.Success
        reduce {
            state.copy(
                whatLabel = proposalTags.value.whatTags.filterNot {
                    state.whatLabel == it
                }.random()
            )
        }
    }

    private fun handleProposalsClick() = intent {
        val encodedUrl = URLEncoder.encode(webViewConfig.proposalsUrl, "UTF-8")
        postSideEffect(HomeSideEffect.NavigateToWebViewScreen(encodedUrl))
    }

    private fun handleOnPermissionGranted() = intent {
        updateDeviceTokenUseCase()
    }
}
