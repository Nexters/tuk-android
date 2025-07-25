package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun loginWithGoogle(accountId: String): Result<Unit> {
        return loginRepository.googleLogin(accountId)
    }
}