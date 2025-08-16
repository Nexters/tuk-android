package com.plottwist.core.domain.onboarding.usecase

import com.plottwist.core.domain.onboarding.OnboardingRepository
import javax.inject.Inject

class GetVersionNameUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): String {
        return onboardingRepository.getVersionName()
    }
}
