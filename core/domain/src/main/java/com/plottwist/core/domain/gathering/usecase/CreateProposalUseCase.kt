package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.Proposal
import com.plottwist.core.domain.model.Purposes
import javax.inject.Inject

class CreateProposalUseCase @Inject constructor(
    private val gatheringRepository: GatheringRepository
) {
    suspend operator fun invoke(
        gatheringId: Long?,
        whereTag: String,
        whenTag: String,
        whatTag: String
    ): Result<Proposal> {
        return gatheringRepository.createPropose(
            gatheringId = gatheringId,
            whereTag = whereTag,
            whenTag = whenTag,
            whatTag = whatTag
        )
    }
}
