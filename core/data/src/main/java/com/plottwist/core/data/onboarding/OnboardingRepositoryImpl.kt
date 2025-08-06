package com.plottwist.core.data.onboarding

import com.plottwist.core.domain.onboarding.OnboardingRepository
import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoRequest
import com.plottwist.core.network.service.OnboardingService
import com.plottwist.core.preference.datasource.AuthDataSource
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingService: OnboardingService,
    private val authDataSource: AuthDataSource,
) : OnboardingRepository {
    override suspend fun updateOnboardingInfo(
        name: String
    ): Result<Unit> {
        val result = onboardingService.updateOnboardingInfo(UpdateOnboardingInfoRequest(name))

        if (result.success) {
            authDataSource.setOnboardingCompleted(true).collect()
            return Result.success(Unit)
        }

        return Result.failure(Exception("Fail to update onboarding info"))
    }
}
