package com.plottwist.core.preference.datasource

import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun getAccessToken(): Flow<String?>
    fun getRefreshToken(): Flow<String?>
    fun getOnboardingCompleted(): Flow<Boolean?>

    fun setAccessToken(accessToken: String): Flow<Unit>
    fun setRefreshToken(refreshToken: String): Flow<Unit>
    fun setOnboardingCompleted(completed: Boolean): Flow<Unit>

    fun clear(): Flow<Unit>
}
