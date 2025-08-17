package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class ReissueTokensUseCase @Inject constructor(
    private val loginRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return loginRepository.reissueTokens()
    }
}
