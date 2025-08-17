package com.plottwist.core.data.push.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.domain.push.repository.PushRepository
import com.plottwist.core.network.model.auth.DeviceInfo
import com.plottwist.core.network.model.auth.DeviceInfoRequest
import com.plottwist.core.network.service.OnboardingService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class PushRepositoryImpl @Inject constructor(
    private val onboardingService: OnboardingService,
    private val deviceInfoProvider: DeviceInfoProvider,
    private val firebaseMessaging: FirebaseMessaging
) : PushRepository {
    override suspend fun updateFcmToken() : Result<Unit> {
        return try {
            val fcmToken = getFcmToken().firstOrNull()?.getOrNull()
            val deviceInfo =
                DeviceInfoRequest(
                    DeviceInfo(
                        deviceId = deviceInfoProvider.getDeviceSSAID(),
                        deviceType = deviceInfoProvider.getDeviceType(),
                        appVersion = deviceInfoProvider.getAppVersion(),
                        osVersion = deviceInfoProvider.getOsVersion(),
                        deviceToken = fcmToken
                    )
                )


            val result = onboardingService.updateDeviceToken(deviceInfo)

            return if(result.success) Result.success(Unit)
            else Result.failure(Exception("Fail Update FCM Token"))
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    override fun getFcmToken() : Flow<Result<String>> {
         return callbackFlow {
            firebaseMessaging.token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    trySend(Result.failure(Exception("Fail Get FCM Token")))
                    return@addOnCompleteListener
                }
                trySend(Result.success(task.result))
            }
            awaitClose()
        }
    }
}
