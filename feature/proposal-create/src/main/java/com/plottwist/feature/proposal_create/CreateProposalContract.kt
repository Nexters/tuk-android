package com.plottwist.feature.proposal_create

import com.plottwist.feature.proposal_create.model.GatheringUiModel

data class CreateProposalState(
    val whereLabel: String = "",
    val whenLabel: String = "",
    val whatLabel: String = "",
    val selectedGathering : GatheringUiModel? = null
)

sealed class CreateProposalAction {
    data object ClickBack: CreateProposalAction()
    data object ClickSelectGathering: CreateProposalAction()
    data class SelectedGathering(
        val gatheringId: Long,
        val gatheringName: String
    ): CreateProposalAction()
    data object ClickCloseSelectedGathering: CreateProposalAction()
    data object ClickPropose : CreateProposalAction()
}

sealed class CreateProposalSideEffect {
    data object NavigateBack: CreateProposalSideEffect()
    data class NavigateToSelectGathering(val selectedGatheringId: Long?): CreateProposalSideEffect()
    data class NavigateToCompletePropose(val proposalId: Long): CreateProposalSideEffect()
}
