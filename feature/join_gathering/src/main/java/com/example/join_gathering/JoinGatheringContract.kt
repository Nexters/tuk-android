package com.example.join_gathering

sealed class JoinGatheringState {
    data object Idle: JoinGatheringState()
    data object Loading: JoinGatheringState()
    data object Success : JoinGatheringState()
    data class Error(val message: String) : JoinGatheringState()
}

sealed class JoinGatheringAction  {
    data class ClickJoin(val gatheringId: Long) : JoinGatheringAction()
    data class OnJoinSuccess(val gatheringId: Long): JoinGatheringAction()
    data class OnJoinError(val message: String): JoinGatheringAction()
}

sealed class JoinGatheringSideEffect {
    data class NavigateToGatheringDetail(val gatheringId: Long) : JoinGatheringSideEffect()
    data class ShowToast(val message: String) : JoinGatheringSideEffect()
}