package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: AuthRepository
) {

    suspend fun loginWithGoogle(accountId: String): Result<Boolean> {
        return loginRepository.googleLogin(accountId)
    }
}
