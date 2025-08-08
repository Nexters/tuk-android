package com.plottwist.feature.proposal_create.gathering_proposal

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateGatheringProposalViewModel @Inject constructor() : ContainerHost<CreateGatheringProposalState, CreateGatheringProposalSideEffect>, ViewModel() {

    override val container = container<CreateGatheringProposalState, CreateGatheringProposalSideEffect>(CreateGatheringProposalState())

    fun handleAction(action: CreateGatheringProposalAction) {
        when (action) {
            CreateGatheringProposalAction.ClickBack -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(CreateGatheringProposalSideEffect.NavigateBack)
    }
}
