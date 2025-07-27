package com.plottwist.core.domain.auth.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun googleLogin(accountId: String): Result<Unit>
    fun checkLoginStatue(): Flow<Boolean>
}
