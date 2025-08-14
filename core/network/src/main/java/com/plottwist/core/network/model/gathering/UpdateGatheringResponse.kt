package com.plottwist.core.network.model.gathering

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable


@Serializable
data class UpdateGatheringResponse(
    val success: Boolean,
    val data: UpdateGatheringData,
    val meta: Meta?
)

@Serializable
data class UpdateGatheringData(
    val gatheringId: Long,
    val gatheringName: String,
    val intervalDays: Int
)
