package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.GatheringDetail
import javax.inject.Inject

class GetGatheringDetailUseCase @Inject constructor(
    private val gatheringRepository: GatheringRepository
) {
    suspend operator fun invoke(gatheringId: Long): Result<GatheringDetail> {
        return gatheringRepository.getGatheringDetail(gatheringId)
    }
}
