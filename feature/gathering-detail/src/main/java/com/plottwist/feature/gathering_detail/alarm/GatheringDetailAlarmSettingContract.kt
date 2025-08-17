package com.plottwist.feature.gathering_detail.alarm

data class GatheringDetailAlarmSettingState(
    val gatheringId: Long = 0,
    val selectedIntervalDays: Int = 0
)

sealed class GatheringDetailAlarmSettingAction {
    data object ClickBack: GatheringDetailAlarmSettingAction()
    data class ClickOption(val days: Int): GatheringDetailAlarmSettingAction()
    data object ClickSave: GatheringDetailAlarmSettingAction()
}

sealed class GatheringDetailAlarmSettingSideEffect {
    data object NavigateBack: GatheringDetailAlarmSettingSideEffect()
    data class NavigateGatheringDetail(val gatheringId: Long) : GatheringDetailAlarmSettingSideEffect()
}
