package com.plottwist.core.domain.auth.usecase

import com.plottwist.core.domain.auth.repository.LoginRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun logoutWithGoogle() {
        loginRepository.logout().first()
    }
}
