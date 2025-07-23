package com.plottwist.core.network.service

import com.plottwist.core.network.model.auth.GoogleLoginRequest
import com.plottwist.core.network.model.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/v1/auth/login/google")
    suspend fun googleLogin(
        @Body googleLoginRequest: GoogleLoginRequest
    ): LoginResponse
}
