package com.example.auth.repository

interface LoginRepository {
    suspend fun googleLogin(accountId: String): Result<Unit>
}