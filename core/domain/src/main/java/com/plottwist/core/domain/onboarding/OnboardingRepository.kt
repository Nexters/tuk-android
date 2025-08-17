package com.plottwist.core.domain.onboarding

import com.plottwist.core.domain.model.MemberInfo

interface OnboardingRepository {
    suspend fun updateOnboardingInfo(
        name: String
    ): Result<Unit>

    suspend fun getMemberInfo(): Result<MemberInfo>

    fun getVersionName() : String
}
