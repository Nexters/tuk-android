package com.plottwist.core.preference.datasource

import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun getAccessToken(): Flow<String?>
    fun getRefreshToken(): Flow<String?>
    fun getMemberName(): Flow<String?>

    fun setAccessToken(accessToken: String): Flow<Unit>
    fun setRefreshToken(refreshToken: String): Flow<Unit>
    fun setMemberName(name: String): Flow<Unit>

    fun clear(): Flow<Unit>
}
