package com.plottwist.core.network.model.gathering

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable

@Serializable
data class GatheringsResponse(
    val success: Boolean,
    val data: GatheringsData,
    val meta: Meta?
)

@Serializable
data class GatheringsData(
    val totalCount: Int,
    val gatheringOverviews: List<GatheringOverviewsData>
)

@Serializable
data class GatheringOverviewsData(
    val gatheringId: Long,
    val gatheringName: String,
    val lastPushRelativeTime: String
)
