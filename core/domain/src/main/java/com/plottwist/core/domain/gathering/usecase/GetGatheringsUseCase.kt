package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.Gatherings
import javax.inject.Inject

class GetGatheringsUseCase @Inject constructor(
    private val gatheringRepository: GatheringRepository
) {
    suspend operator fun invoke(): Result<Gatherings> {
        return gatheringRepository.getGatherings()
    }
}
