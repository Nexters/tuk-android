package com.plottwist.core.domain.auth.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun googleLogin(accountId: String): Result<Boolean>
    fun checkLoginStatus(): Flow<Boolean>
    fun logout(): Flow<Unit>
    fun getAccessToken() : Flow<String?>
    suspend fun deleteAccount(): Result<Boolean>
}
