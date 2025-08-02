package com.plottwist.feature.proposal_create

data class CreateProposalState(
    val whereLabel: String = "",
    val whenLabel: String = "",
    val whatLabel: String = "",
    val isGatheringSelected : Boolean = false
)

sealed class CreateProposalAction {
    data object ClickClose: CreateProposalAction()
}

sealed class CreateProposalSideEffect {
    data object NavigateBack: CreateProposalSideEffect()
}
