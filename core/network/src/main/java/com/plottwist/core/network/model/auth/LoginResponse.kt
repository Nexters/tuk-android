package com.plottwist.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val success: Boolean,
    val data: LoginData,
    val meta: Meta?
)

@Serializable
data class LoginData(
    val memberId: Long,
    val accessToken: String,
    val refreshToken: String,
    val isFirstLogin: Boolean
)

@Serializable
data class Meta(
    val errorType: String,
    val errorMessage: String?
)


@Serializable
data class TokenResponse(
    val success: Boolean,
    val data: TokenData,
    val meta: Meta?
)

@Serializable
data class TokenData(
    val accessToken: String,
    val refreshToken: String
)
