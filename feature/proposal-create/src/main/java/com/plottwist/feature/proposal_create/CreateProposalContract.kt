package com.plottwist.feature.proposal_create

data class CreateProposalState(
    val whereLabel: String = "",
    val whenLabel: String = "",
    val whatLabel: String = "",
    val selectedGatheringId: Long? = null
)

sealed class CreateProposalAction {
    data object ClickClose: CreateProposalAction()
    data object ClickSelectGathering: CreateProposalAction()
}

sealed class CreateProposalSideEffect {
    data object NavigateBack: CreateProposalSideEffect()
    data class NavigateToSelectGathering(val selectedGatheringId: Long?): CreateProposalSideEffect()
}
