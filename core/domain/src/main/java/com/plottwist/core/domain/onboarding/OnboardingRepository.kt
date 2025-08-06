package com.plottwist.core.domain.onboarding

interface OnboardingRepository {
    suspend fun updateOnboardingInfo(
        name: String
    ): Result<Unit>
}
