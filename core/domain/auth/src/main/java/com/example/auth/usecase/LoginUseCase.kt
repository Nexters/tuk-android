package com.example.auth.usecase

import com.example.auth.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun loginWithGoogle(accountId: String): Result<Unit> {
        return loginRepository.googleLogin(accountId)
    }
}