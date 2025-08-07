package com.plottwist.invite_gathering

data class InviteGatheringState(
    val isLoading: Boolean = false,
    val url : String,
)

sealed class InviteGatheringAction {
    data object ClickBack : InviteGatheringAction()
}

sealed class InviteGatheringSideEffect {
    data object NavigateBack : InviteGatheringSideEffect()
}
