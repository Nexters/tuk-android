package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun deleteAccount() {
        authRepository.deleteAccount()
    }
}
