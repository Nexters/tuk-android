package com.plottwist.core.domain.common

data class BaseResponse<T>(
    val success: Boolean,
    val data: T?,
    val meta: ErrorMeta?
)

data class ErrorMeta(
    val errorType: String?,
    val errorMessage: String?
)
