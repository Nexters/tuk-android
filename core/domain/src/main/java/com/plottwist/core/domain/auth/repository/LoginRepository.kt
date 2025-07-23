package com.plottwist.core.domain.auth.repository

interface LoginRepository {
    suspend fun googleLogin(accountId: String): Result<Unit>
}