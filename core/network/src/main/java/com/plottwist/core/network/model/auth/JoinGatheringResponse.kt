package com.plottwist.core.network.model.auth

data class JoinGatheringResponse(
    val success: Boolean,
    val data: JoinGathering?,
    val meta: ErrorInfo?
)

data class JoinGathering(
    val id: Long
)

data class ErrorInfo(
    val errorType: String?,
    val errorMessage: String?
)