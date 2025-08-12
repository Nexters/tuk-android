package com.plottwist.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class JoinGatheringResponse(
    val success: Boolean,
    val data: JoinGathering?,
    val meta: ErrorInfo?
)

@Serializable
data class JoinGathering(
    val id: Long
)

@Serializable
data class ErrorInfo(
    val errorType: String?,
    val errorMessage: String?
)

@Serializable
data class GetGatheringNameResponse(
    val success: Boolean,
    val data: GetGathering?,
    val meta: ErrorInfo?
)

@Serializable
data class GetGathering(
    val gatheringId: Long,
    val gatheringName: String
)
