package com.plottwist.core.network.service

import com.plottwist.core.network.model.auth.GoogleLoginRequest
import com.plottwist.core.network.model.auth.JoinGatheringResponse
import com.plottwist.core.network.model.auth.LoginResponse
import com.plottwist.core.network.model.auth.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {
    @POST("/api/v1/auth/login/google")
    suspend fun googleLogin(
        @Body googleLoginRequest: GoogleLoginRequest
    ): LoginResponse

    @POST("/api/v1/gatherings/{gatheringId}/members")
    suspend fun joinGathering(
        @Path("gatheringId") gatheringId: Long
    ): JoinGatheringResponse

    @DELETE("/api/v1/members")
    suspend fun deleteMember(): LogoutResponse
}
