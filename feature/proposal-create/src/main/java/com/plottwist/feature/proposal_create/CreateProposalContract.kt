package com.plottwist.feature.proposal_create

data class CreateProposalState(
    val title: String = "",
    val content: String = "",
)

sealed class CreateProposalAction {
    data object ClickClose: CreateProposalAction()
}

sealed class CreateProposalSideEffect {
    data object NavigateBack: CreateProposalSideEffect()
}
