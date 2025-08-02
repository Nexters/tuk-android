package com.plottwist.feature.gathering_detail

import com.plottwist.core.domain.model.GatheringDetail

data class GatheringDetailState(
    val gatheringDetail: GatheringDetail = GatheringDetail()
)

enum class GatheringDetailAction {

}

enum class GatheringDetailSideEffect {

}
