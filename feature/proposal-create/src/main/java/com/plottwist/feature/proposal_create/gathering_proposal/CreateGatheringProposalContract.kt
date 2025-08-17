package com.plottwist.feature.proposal_create.gathering_proposal

import androidx.compose.foundation.text.input.TextFieldState

data class CreateGatheringProposalState(
    val isLoading: Boolean = false,
    val whenLabel : TextFieldState = TextFieldState(),
    val whereLabel : TextFieldState = TextFieldState(),
    val whatLabel : TextFieldState = TextFieldState(),
    val whenTags : List<String> = emptyList(),
    val whereTags : List<String> = emptyList(),
    val whatTags : List<String> = emptyList(),
    val gatheringName: String,
    val gatheringId: Long,
    val isReady: Boolean = false
)

sealed class CreateGatheringProposalAction {
    data object ClickBack : CreateGatheringProposalAction()
    data object ClickWhenRefresh : CreateGatheringProposalAction()
    data object ClickWhereRefresh : CreateGatheringProposalAction()
    data object ClickWhatRefresh : CreateGatheringProposalAction()
    data object ClickNext: CreateGatheringProposalAction()
}

sealed class CreateGatheringProposalSideEffect {
    data object NavigateBack : CreateGatheringProposalSideEffect()
}
