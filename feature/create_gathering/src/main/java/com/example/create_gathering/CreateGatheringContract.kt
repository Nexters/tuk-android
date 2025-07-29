package com.example.create_gathering

import com.example.create_gathering.model.GatheringTag
import com.example.create_gathering.model.TagCategory

data class CreateGatheringState(
    val currentPage: Int = 0,
    val gatheringName: String = "",
    val intervalDays:Int = 0,
    val tags: List<GatheringTag> = emptyList(),
    val tagCategories: List<TagCategory> = emptyList(),
    val description: String = ""
)

sealed class CreateGatheringAction {
    data object ClickNext : CreateGatheringAction()
    data object ClickPrev : CreateGatheringAction()
    data object ClickSkip : CreateGatheringAction()
    data object SubmitGathering : CreateGatheringAction()

    data class UpdateGatheringName(val name: String) : CreateGatheringAction()
    data class UpdateIntervalDays(val intervalDays: Int) : CreateGatheringAction()
    data class ToggleTag(val tag: GatheringTag) : CreateGatheringAction()
}

sealed class CreateGatheringSideEffect {
    data object NavigateToHomeScreen: CreateGatheringSideEffect()
    data object NavigateToBack:CreateGatheringSideEffect()
}
