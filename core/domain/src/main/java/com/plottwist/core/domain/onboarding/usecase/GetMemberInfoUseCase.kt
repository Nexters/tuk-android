package com.plottwist.core.domain.onboarding.usecase

import com.plottwist.core.domain.model.MemberInfo
import com.plottwist.core.domain.onboarding.OnboardingRepository
import javax.inject.Inject

class GetMemberInfoUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(): Result<MemberInfo> {
        return onboardingRepository.getMemberInfo()
    }
}
