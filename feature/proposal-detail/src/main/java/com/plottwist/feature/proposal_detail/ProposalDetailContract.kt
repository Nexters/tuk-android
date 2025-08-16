package com.plottwist.feature.proposal_detail

data class ProposalDetailState(
    val isLoading: Boolean = false,
    val proposalId: Long? = null
)

sealed class ProposalDetailAction {
    data object ClickBack : ProposalDetailAction()
    data object ClickAccept : ProposalDetailAction()
    data object ClickReject : ProposalDetailAction()
}

sealed class ProposalDetailSideEffect {
    data object NavigateBack : ProposalDetailSideEffect()
    data object ProposalAccepted : ProposalDetailSideEffect()
    data object ProposalRejected : ProposalDetailSideEffect()
    data class ShowToast(val message: String) : ProposalDetailSideEffect()
}
