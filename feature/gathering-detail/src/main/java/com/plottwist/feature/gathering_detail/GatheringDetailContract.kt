package com.plottwist.feature.gathering_detail

import com.plottwist.core.domain.model.GatheringDetail

data class GatheringDetailState(
    val gatheringDetail: GatheringDetail = GatheringDetail()
)

sealed class GatheringDetailAction {
    data object ClickBack: GatheringDetailAction()
}

sealed class GatheringDetailSideEffect {
    data object NavigateBack: GatheringDetailSideEffect()
}
