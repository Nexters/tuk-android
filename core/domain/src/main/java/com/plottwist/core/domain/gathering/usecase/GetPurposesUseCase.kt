package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.Purposes
import javax.inject.Inject

class GetPurposesUseCase @Inject constructor(
    private val gatheringRepository: GatheringRepository
) {
    suspend operator fun invoke(): Result<Purposes> {
        return gatheringRepository.getPurposes()
    }
}
