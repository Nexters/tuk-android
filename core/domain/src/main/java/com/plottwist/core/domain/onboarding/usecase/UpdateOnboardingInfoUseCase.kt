package com.plottwist.core.domain.onboarding.usecase

import com.plottwist.core.domain.onboarding.OnboardingRepository
import javax.inject.Inject

class UpdateOnboardingInfoUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(
        name: String
    ): Result<Unit> {
        return onboardingRepository.updateOnboardingInfo(name)
    }
}
