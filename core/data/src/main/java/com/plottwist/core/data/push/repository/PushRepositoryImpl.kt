package com.plottwist.core.data.push.repository

import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.domain.push.repository.PushRepository
import com.plottwist.core.network.model.auth.DeviceInfo
import com.plottwist.core.network.service.OnboardingService
import javax.inject.Inject

class PushRepositoryImpl @Inject constructor(
    private val onboardingService: OnboardingService,
    private val deviceInfoProvider: DeviceInfoProvider
) : PushRepository {
    override suspend fun updateFcmToken(fcmToken: String) : Result<Unit> {
        return try {
            val deviceInfo = DeviceInfo(
                deviceId = deviceInfoProvider.getDeviceSSAID(),
                deviceType = deviceInfoProvider.getDeviceType(),
                appVersion = deviceInfoProvider.getAppVersion(),
                osVersion = deviceInfoProvider.getOsVersion(),
                deviceToken = fcmToken
            )

            val result = onboardingService.updateDeviceToken(deviceInfo)

            return if(result.success) Result.success(Unit)
            else Result.failure(Exception("Fail Update FCM Token"))
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}
