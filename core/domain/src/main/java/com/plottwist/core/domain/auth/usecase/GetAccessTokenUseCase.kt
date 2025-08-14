package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(): Flow<String?> {
        return loginRepository.getAccessToken()
    }
}
