package com.plottwist.core.data.auth.repository

import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.domain.auth.repository.LoginRepository
import com.plottwist.core.preference.datasource.AuthDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val authDataSource: AuthDataSource,
    private val deviceInfoProvider: DeviceInfoProvider
): LoginRepository {

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


            val response = loginService.requestGoogleLogin(request)

            if (response.isSuccessful) {
                val body = response.body()

                if (body?.success == true && body.data != null) {
                    val data = body.data

                    if (data != null) {
                        authDataSource.setAccessToken(data.accessToken)
                        authDataSource.setRefreshToken(data.refreshToken)
                    }

                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Login failed: ${body?.meta?.errorMessage ?: "Unknown error"}"))
                }
            } else {
                Result.failure(Exception("HTTP ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}