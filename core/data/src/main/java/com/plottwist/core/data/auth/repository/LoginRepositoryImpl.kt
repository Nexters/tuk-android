package com.plottwist.core.data.auth.repository

import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.domain.auth.repository.LoginRepository
import com.plottwist.core.network.model.auth.DeviceInfo
import com.plottwist.core.network.model.auth.GoogleLoginRequest
import com.plottwist.core.network.service.AuthApiService
import com.plottwist.core.preference.datasource.AuthDataSource
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val loginService: AuthApiService,
    private val authDataSource: AuthDataSource,
    private val deviceInfoProvider: DeviceInfoProvider
) : LoginRepository {

    override suspend fun googleLogin(accountId: String): Result<Unit> {
        return try {
            val deviceInfo = DeviceInfo(
                deviceId = deviceInfoProvider.getDeviceSSAID(),
                deviceType = deviceInfoProvider.getDeviceType(),
                appVersion = deviceInfoProvider.getAppVersion(),
                osVersion = deviceInfoProvider.getOsVersion()
            )

            val request = GoogleLoginRequest(
                idToken = accountId,
                deviceInfo = deviceInfo
            )

            val response = loginService.googleLogin(request)

            if (response.success) {
                val result = response.data
                authDataSource.setAccessToken(result.accessToken)
                authDataSource.setRefreshToken(result.refreshToken)

                Result.success(Unit)

            } else {
                Result.failure(Exception("Fail Google Login"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
