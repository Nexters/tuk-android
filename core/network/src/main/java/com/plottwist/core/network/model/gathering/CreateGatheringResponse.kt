package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class CreateGatheringResponse(
    val success: Boolean,
    val data: Data?,
    val meta: Meta?
)

@Serializable
data class Data(
    val gatheringId: Int
)

@Serializable
data class Meta(
    val errorType: String,
    val errorMessage: String
)
