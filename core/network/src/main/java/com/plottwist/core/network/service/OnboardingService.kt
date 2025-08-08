package com.plottwist.core.network.service

import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoRequest
import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.PUT

interface OnboardingService {
    @PUT("/api/v1/onboarding")
    suspend fun updateOnboardingInfo(
        @Body request: UpdateOnboardingInfoRequest
    ): UpdateOnboardingInfoResponse
}
