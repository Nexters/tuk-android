package com.plottwist.core.network.service

import com.plottwist.core.network.model.auth.DeviceInfoRequest
import com.plottwist.core.network.model.onboarding.MemberInfoResponse
import com.plottwist.core.network.model.onboarding.UpdateDeviceInfoResponse
import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoRequest
import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface OnboardingService {
    @PUT("/api/v1/onboarding")
    suspend fun updateOnboardingInfo(
        @Body request: UpdateOnboardingInfoRequest
    ): UpdateOnboardingInfoResponse

    @POST("/api/v1/device/token")
    suspend fun updateDeviceToken(
        @Body deviceInfo: DeviceInfoRequest
    ): UpdateDeviceInfoResponse

    @GET("/api/v1/members/me")
    suspend fun getMemberInfo(): MemberInfoResponse
}
