package com.example.create_gathering

import com.example.create_gathering.model.GatheringHashTag

data class CreateGatheringState(
    val currentPage: Int = 0,
    val gatheringName: String = "",
    val lastGathering: String = "",
    val frequencyGathering:String = "",
    val tags: List<String> = emptyList(),
    val description: String = ""
)

sealed class CreateGatheringAction {
    data object ClickNext : CreateGatheringAction()
    data object ClickPrev : CreateGatheringAction()
    data object ClickSkip : CreateGatheringAction()
    data object SubmitGathering : CreateGatheringAction()

    data class UpdateGatheringName(val name: String) : CreateGatheringAction()
    data class UpdateLastGatheringType(val type: String) : CreateGatheringAction()
    data class UpdateFrequency(val frequency: String) : CreateGatheringAction()
    data class ToggleTag(val tag: GatheringHashTag) : CreateGatheringAction()
    data class AddTag(val tag: GatheringHashTag) : CreateGatheringAction()
}

sealed class CreateGatheringSideEffect {
    data object NavigateToHomeScreen: CreateGatheringSideEffect()
    data object NavigateToBack:CreateGatheringSideEffect()
}
