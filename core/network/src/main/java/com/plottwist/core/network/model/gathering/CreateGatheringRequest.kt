package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class CreateGatheringRequest(
    val gatheringName: String,
    val gatheringIntervalDays: Int,
    val tagIds: List<Int>
)
