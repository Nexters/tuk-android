package com.plottwist.core.data.auth

import com.plottwist.core.network.TokenProvider
import com.plottwist.core.preference.datasource.AuthDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class AuthTokenProvider @Inject constructor(
    private val authDataSource: AuthDataSource,
) : TokenProvider {
    override suspend fun getAccessToken(): String? {
        return authDataSource.getAccessToken().firstOrNull()
    }

    override suspend fun getRefreshToken(): String? {
        return authDataSource.getRefreshToken().firstOrNull()
    }

    override suspend fun setAccessToken(accessToken: String) {
        authDataSource.setAccessToken(accessToken).collect()
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        authDataSource.setRefreshToken(refreshToken).collect()
    }
}
