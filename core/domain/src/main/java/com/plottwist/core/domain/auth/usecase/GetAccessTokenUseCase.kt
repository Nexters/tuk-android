package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val loginRepository: AuthRepository
) {
    operator fun invoke(): Flow<String?> {
        return loginRepository.getAccessToken()
    }
}
