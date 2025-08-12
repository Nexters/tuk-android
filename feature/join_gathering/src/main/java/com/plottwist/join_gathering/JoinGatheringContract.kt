package com.plottwist.join_gathering

data class JoinGatheringState (
    val gatheringId : Long = 0
)

sealed class JoinGatheringAction  {
    data object ClickJoin : JoinGatheringAction()
}

sealed class JoinGatheringSideEffect {
    data class NavigateToGatheringDetail(val gatheringId: Long) : JoinGatheringSideEffect()
}
