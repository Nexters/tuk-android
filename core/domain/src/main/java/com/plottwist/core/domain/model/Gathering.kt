package com.plottwist.core.domain.model

data class Gatherings(
    val totalCount: Int = 0,
    val gatheringOverviews: List<GatheringOverviews> = emptyList()
)

data class GatheringOverviews(
    val gatheringId: Long,
    val gatheringName: String,
    val monthsSinceLastGathering: Int
)
