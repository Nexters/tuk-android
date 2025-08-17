package com.plottwist.core.network.service

import com.plottwist.core.network.model.auth.GoogleLoginRequest
import com.plottwist.core.network.model.auth.LoginResponse
import com.plottwist.core.network.model.auth.TokenRequest
import com.plottwist.core.network.model.auth.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/v1/auth/login/google")
    suspend fun googleLogin(
        @Body googleLoginRequest: GoogleLoginRequest
    ): LoginResponse

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(
        @Body tokenRequest : TokenRequest
    ) : TokenResponse
}
