package com.plottwist.feature.proposal_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProposalDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ContainerHost<ProposalDetailState, ProposalDetailSideEffect>, ViewModel() {

    override val container = container<ProposalDetailState, ProposalDetailSideEffect>(ProposalDetailState(
        proposalId = savedStateHandle.toRoute<Route.ProposalDetail>().proposalId
    ))

    fun handleAction(action: ProposalDetailAction) {
        when (action) {
            ProposalDetailAction.ClickBack -> {
                navigateBack()
            }
            ProposalDetailAction.ClickAccept -> {
                acceptProposal()
            }
            ProposalDetailAction.ClickReject -> {
                rejectProposal()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(ProposalDetailSideEffect.NavigateBack)
    }

    private fun acceptProposal() = intent {
        // TODO: Implement actual accept logic
        postSideEffect(ProposalDetailSideEffect.ProposalAccepted)
        postSideEffect(ProposalDetailSideEffect.ShowToast("제안이 수락되었습니다."))
    }

    private fun rejectProposal() = intent {
        // TODO: Implement actual reject logic
        postSideEffect(ProposalDetailSideEffect.ProposalRejected)
        postSideEffect(ProposalDetailSideEffect.ShowToast("제안이 거절되었습니다."))
    }
}
