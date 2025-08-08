package com.plottwist.feature.proposal_create.gathering_proposal

data class CreateGatheringProposalState(
    val isLoading: Boolean = false
)

sealed class CreateGatheringProposalAction {
    data object ClickBack : CreateGatheringProposalAction()
}

sealed class CreateGatheringProposalSideEffect {
    data object NavigateBack : CreateGatheringProposalSideEffect()
}
