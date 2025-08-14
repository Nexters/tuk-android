package com.plottwist.feature.gathering_detail

import com.plottwist.core.domain.model.GatheringDetail

data class GatheringDetailState(
    val gatheringDetail: GatheringDetail = GatheringDetail()
)

sealed class GatheringDetailAction {
    data object ClickBack: GatheringDetailAction()
    data object ClickSentProposal: GatheringDetailAction()
    data object ClickReceivedProposal: GatheringDetailAction()
    data object ClickInviteMember: GatheringDetailAction()
    data object ClickProposal: GatheringDetailAction()
    data object ClickAlarmSetting: GatheringDetailAction()
}

sealed class GatheringDetailSideEffect {
    data object NavigateBack: GatheringDetailSideEffect()
    data class NavigateToWebView(val encodedUrl: String): GatheringDetailSideEffect()
    data class NavigateInviteGatheringScreen(val encodedUrl: String): GatheringDetailSideEffect()
    data class NavigateToCreateGatheringProposal(
        val gatheringId: Long,
        val gatheringName: String
    ): GatheringDetailSideEffect()
    data class NavigateToGatheringDetailAlarmSetting(
        val gatheringId: Long,
        val selectedIntervalDays: Long
    ): GatheringDetailSideEffect()
}
