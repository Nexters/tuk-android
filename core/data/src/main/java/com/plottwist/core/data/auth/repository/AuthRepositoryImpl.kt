package com.plottwist.core.data.auth.repository

import com.plottwist.core.data.common.DeviceInfoProvider
import com.plottwist.core.domain.auth.repository.AuthRepository
import com.plottwist.core.domain.onboarding.OnboardingRepository
import com.plottwist.core.domain.push.repository.PushRepository
import com.plottwist.core.network.model.auth.DeviceInfo
import com.plottwist.core.network.model.auth.GoogleLoginRequest
import com.plottwist.core.network.model.auth.TokenRequest
import com.plottwist.core.network.model.onboarding.MemberNameRequest
import com.plottwist.core.network.service.AuthApiService
import com.plottwist.core.network.service.TukApiService
import com.plottwist.core.preference.datasource.AuthDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val authDataSource: AuthDataSource,
    private val deviceInfoProvider: DeviceInfoProvider,
    private val pushRepository: PushRepository,
    private val tukApiService: TukApiService,
    private val onboardingRepository: OnboardingRepository
) : AuthRepository {

    override suspend fun googleLogin(accountId: String): Result<Boolean> {
        return try {
            val fcmToken = pushRepository.getFcmToken().firstOrNull()?.getOrNull()
            val deviceInfo = DeviceInfo(
                deviceId = deviceInfoProvider.getDeviceSSAID(),
                deviceType = deviceInfoProvider.getDeviceType(),
                appVersion = deviceInfoProvider.getAppVersion(),
                osVersion = deviceInfoProvider.getOsVersion(),
                deviceToken = fcmToken
            )

            val request = GoogleLoginRequest(
                idToken = accountId,
                deviceInfo = deviceInfo
            )

            val response = authApiService.googleLogin(request)

            if (response.success) {
                val result = response.data
                authDataSource.setAccessToken(result.accessToken).collect()
                authDataSource.setRefreshToken(result.refreshToken).collect()

                if(!result.isFirstLogin) {
                    val name = onboardingRepository.getMemberInfo().getOrNull()?.name
                    if(name.isNullOrEmpty()) {
                        return Result.success(false)
                    }else {
                        Result.success(true)
                    }
                } else {
                    Result.success(false)
                }

            } else {
                Result.failure(Exception("Fail Google Login"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun checkLoginStatus(): Flow<Boolean> {
        return combine(
            authDataSource.getAccessToken(),
            authDataSource.getMemberName()
        ) { accessToken, name ->
            accessToken?.isNotEmpty() ?: false && !name.isNullOrEmpty()
        }
    }

    override fun logout(): Flow<Unit> {
        return authDataSource.clear()
    }

    override fun getAccessToken(): Flow<String?> {
        return authDataSource.getAccessToken()
    }

    override suspend fun deleteAccount(): Result<Boolean> {
        return try {
            val response = tukApiService.deleteMember()

            if (response.success) {
                authDataSource.clear().collect()
                Result.success(true)
            } else {
                Result.failure(Exception("Fail Delete member"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMemberName(name: String): Result<Unit> {
        return try {
            val response = tukApiService.updateMemberName(
                MemberNameRequest(name)
            )

            if (response.success) {
                setMemberName(name).collect()
                Result.success(Unit)
            } else {
                Result.failure(Exception("Fail Delete member"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getMemberName(): Flow<String?> {
        return authDataSource.getMemberName()
    }

    override fun setMemberName(name: String): Flow<Unit> {
        return authDataSource.setMemberName(name)
    }

    override suspend fun reissueTokens(): Result<Unit> {
        try {
            val refreshToken = authDataSource.getRefreshToken().firstOrNull()
            if(refreshToken.isNullOrEmpty()){
                authDataSource.clear()
                return Result.failure(Exception("Fail Reissue Tokens"))
            }
            val result = authApiService.refreshToken(TokenRequest(refreshToken))

            return if (result.success) {
                authDataSource.setAccessToken(result.data.accessToken).collect()
                authDataSource.setRefreshToken(result.data.refreshToken).collect()
                Result.success(Unit)
            } else {
                Result.failure(Exception("Fail Delete member"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
