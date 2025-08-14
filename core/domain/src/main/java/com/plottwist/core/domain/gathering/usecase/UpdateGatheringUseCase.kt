package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.repository.GatheringRepository
import javax.inject.Inject

class UpdateGatheringUseCase @Inject constructor(
    private val repository: GatheringRepository
){
    suspend operator fun invoke(
        gatheringId: Long,
        intervalDays: Int
    ):Result<Unit> {
        return repository.updateGathering(gatheringId, intervalDays)
    }
}
