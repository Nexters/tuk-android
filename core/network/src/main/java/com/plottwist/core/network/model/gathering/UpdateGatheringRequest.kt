package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class UpdateGatheringRequest(
    val gatheringIntervalDays: Int
)

