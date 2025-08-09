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
}

sealed class GatheringDetailSideEffect {
    data object NavigateBack: GatheringDetailSideEffect()
    data class NavigateToWebView(val encodedUrl: String): GatheringDetailSideEffect()
    data class NavigateInviteGatheringScreen(val encodedUrl: String): GatheringDetailSideEffect()
}
