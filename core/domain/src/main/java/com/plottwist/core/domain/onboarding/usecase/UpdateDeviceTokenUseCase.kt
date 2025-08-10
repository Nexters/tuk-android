package com.plottwist.core.domain.onboarding.usecase

import com.plottwist.core.domain.push.repository.PushRepository
import javax.inject.Inject

class UpdateDeviceTokenUseCase @Inject constructor(
    private val pushRepository: PushRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return pushRepository.updateFcmToken()
    }
}
