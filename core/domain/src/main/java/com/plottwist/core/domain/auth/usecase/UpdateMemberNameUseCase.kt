package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class UpdateMemberNameUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String): Result<Unit> {
        return authRepository.updateMemberName(name)
    }
}
