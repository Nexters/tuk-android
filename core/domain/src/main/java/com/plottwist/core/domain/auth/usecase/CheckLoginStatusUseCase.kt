package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val loginRepository: AuthRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return loginRepository.checkLoginStatus()
    }
}
