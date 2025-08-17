package com.plottwist.feature.proposal_create.complete

data class CompleteProposeState(
    val isLoading: Boolean = false,
    val url: String = ""
)

sealed class CompleteProposeAction {
    data object ClickClose : CompleteProposeAction()
}

sealed class CompleteProposeSideEffect {
    data object NavigateToHome : CompleteProposeSideEffect()
}
