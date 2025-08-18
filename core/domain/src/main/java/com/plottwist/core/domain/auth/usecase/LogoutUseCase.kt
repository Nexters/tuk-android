package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val loginRepository: AuthRepository
) {

    suspend fun logoutWithGoogle() {
        loginRepository.resetServerFcmToken().onSuccess {
            loginRepository.logout().collect()
        }.onFailure {
            loginRepository.logout().collect()
        }
    }
}
