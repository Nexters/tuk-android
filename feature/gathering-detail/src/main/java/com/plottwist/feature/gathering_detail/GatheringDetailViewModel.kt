package com.plottwist.feature.gathering_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.GetGatheringDetailUseCase
import com.plottwist.core.ui.navigation.Route
import com.plottwist.core.weburl.WebUrlConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class GatheringDetailViewModel @Inject constructor(
    private val getGatheringDetailUseCase: GetGatheringDetailUseCase,
    private val webUrlConfig: WebUrlConfig,
    savedStateHandle: SavedStateHandle
) : ContainerHost<GatheringDetailState, GatheringDetailSideEffect>, ViewModel() {
    override val container = container<GatheringDetailState, GatheringDetailSideEffect>(
        GatheringDetailState()
    ) {
        fetchGatheringDetail()
    }
    private val gatheringId = savedStateHandle.toRoute<Route.GatheringDetail>().gatheringId

    fun handleAction(action: GatheringDetailAction) {
        when(action) {
            GatheringDetailAction.ClickBack -> {
                navigateBack()
            }

            GatheringDetailAction.ClickReceivedProposal -> {
                handleReceivedProposalClick()
            }

            GatheringDetailAction.ClickSentProposal -> {
                handleSentProposalClick()
            }

            GatheringDetailAction.ClickInviteMember -> {
                handleInviteMemberClick()
            }

            GatheringDetailAction.ClickProposal -> {
                handleCreateGatheringProposalClick()
            }

            GatheringDetailAction.ClickAlarmSetting -> {
                handleAlarmSettingClick()
            }
        }
    }

    private fun fetchGatheringDetail() = intent {
        val result = getGatheringDetailUseCase(gatheringId)

        if(result.isSuccess) {
            reduce {
                state.copy(
                    gatheringDetail = result.getOrNull() ?: return@reduce state
                )
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(GatheringDetailSideEffect.NavigateBack)
    }

    private fun handleReceivedProposalClick() = intent {
        val encodedUrl = URLEncoder.encode(webUrlConfig.sentProposalsUrl,"UTF-8")
        postSideEffect(GatheringDetailSideEffect.NavigateToWebView(encodedUrl))
    }

    private fun handleSentProposalClick() = intent {
        val encodedUrl = URLEncoder.encode(webUrlConfig.sentProposalsUrl,"UTF-8")
        postSideEffect(GatheringDetailSideEffect.NavigateToWebView(encodedUrl))
    }

    private fun handleInviteMemberClick() = intent {
        val url = webUrlConfig.inviteGatheringUrl.replace("{gatheringId}", state.gatheringDetail.gatheringId.toString())
        val encodedUrl = URLEncoder.encode(url,"UTF-8")
        postSideEffect(GatheringDetailSideEffect.NavigateInviteGatheringScreen(encodedUrl))
    }

    private fun handleCreateGatheringProposalClick() = intent {
        postSideEffect(
            GatheringDetailSideEffect.NavigateToCreateGatheringProposal(
                gatheringId = state.gatheringDetail.gatheringId,
                gatheringName = state.gatheringDetail.gatheringName
            )
        )
    }

    private fun handleAlarmSettingClick() = intent {
        postSideEffect(
            GatheringDetailSideEffect.NavigateToGatheringDetailAlarmSetting(
                gatheringId = state.gatheringDetail.gatheringId,
                selectedIntervalDays = state.gatheringDetail.gatheringIntervalDays
            )
        )
    }
}
