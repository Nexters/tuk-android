package com.plottwist.invite_gathering

data class InviteGatheringState(
    val isLoading: Boolean = false,
    val url : String = ""
)

sealed class InviteGatheringAction {
    data object ClickBack : InviteGatheringAction()
    data object ClickCopy : InviteGatheringAction()
    data object ClickShare : InviteGatheringAction()
}

sealed class InviteGatheringSideEffect {
    data object NavigateBack : InviteGatheringSideEffect()
    data class CopyToClipboard(val text: String) : InviteGatheringSideEffect()
    data class ShareContent(val text: String) : InviteGatheringSideEffect()
}
