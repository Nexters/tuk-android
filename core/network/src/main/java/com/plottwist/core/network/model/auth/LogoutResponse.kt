package com.plottwist.core.network.model.auth

data class LogoutResponse(
    val success: Boolean,
    val meta: Meta
) {
    data class Meta(
        val errorType: String,
        val errorMessage: String
    )
}
