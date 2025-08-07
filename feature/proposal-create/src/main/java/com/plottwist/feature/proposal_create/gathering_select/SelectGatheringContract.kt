package com.plottwist.feature.proposal_create.gathering_select

import com.plottwist.feature.proposal_create.model.GatheringUiModel
import com.plottwist.feature.proposal_create.model.SelectedGatheringParam

data class SelectGatheringState(
    val selectedGatheringId: Long? = null,
    val gatherings: List<GatheringUiModel> = emptyList()
)

sealed class SelectGatheringAction {
    data object ClickBack : SelectGatheringAction()
    data class SelectGathering(val gatheringId: Long) : SelectGatheringAction()
    data object ClickPropose : SelectGatheringAction()
}

sealed class SelectGatheringSideEffect {
    data object NavigateBack : SelectGatheringSideEffect()
    data class NavigateToCreateProposal(
        val selectedGathering: SelectedGatheringParam
    ) : SelectGatheringSideEffect()
}
