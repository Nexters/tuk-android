package com.plottwist.core.data.onboarding

import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.data.mapper.toDomainModel
import com.plottwist.core.domain.model.MemberInfo
import com.plottwist.core.domain.onboarding.OnboardingRepository
import com.plottwist.core.network.model.onboarding.UpdateOnboardingInfoRequest
import com.plottwist.core.network.service.OnboardingService
import com.plottwist.core.preference.datasource.AuthDataSource
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingService: OnboardingService,
    private val authDataSource: AuthDataSource,
    private val deviceInfoProvider: DeviceInfoProvider
) : OnboardingRepository {
    override suspend fun updateOnboardingInfo(
        name: String
    ): Result<Unit> {
        try {
            val  result = onboardingService.updateOnboardingInfo(UpdateOnboardingInfoRequest(name))

            if (result.success) {
                authDataSource.setMemberName(name).collect()
                return Result.success(Unit)
            }

            return Result.failure(Exception("Fail to update onboarding info"))
        } catch (e:Exception) {
            return Result.failure(Exception(e))
        }
    }

    override suspend fun getMemberInfo(): Result<MemberInfo> {
        try {
            val result = onboardingService.getMemberInfo()

            if (result.success) {
                authDataSource.setMemberName(result.data.name).collect()
                return Result.success(result.data.toDomainModel())
            }

            return Result.failure(Exception("Fail to get member info"))
        } catch (e:Exception) {
            return Result.failure(Exception(e))
        }
    }

    override fun getVersionName(): String {
        return deviceInfoProvider.getAppVersion()
    }
}
