package com.plottwist.feature.proposal_create.gathering_select

import com.plottwist.feature.proposal_create.model.GatheringUiModel

data class SelectGatheringState(
    val selectedGatheringId: Long? = null,
    val gatherings: List<GatheringUiModel> = emptyList()
)

sealed class SelectGatheringAction {
    data object ClickBack : SelectGatheringAction()
    data class SelectGathering(val gatheringId: Long) : SelectGatheringAction()
}

sealed class SelectGatheringSideEffect {
    data object NavigateBack : SelectGatheringSideEffect()
    data class NavigateToCreateProposal(val gatheringId: Long) : SelectGatheringSideEffect()
}
