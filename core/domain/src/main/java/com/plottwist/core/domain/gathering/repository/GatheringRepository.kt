package com.plottwist.core.domain.gathering.repository

import com.plottwist.core.domain.model.GatheringDetail
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.domain.model.Proposal
import com.plottwist.core.domain.model.Purposes

interface GatheringRepository {
    suspend fun getGatherings(): Result<Gatherings>
    suspend fun getGatheringDetail(gatheringId: Long): Result<GatheringDetail>
    suspend fun getPurposes(): Result<Purposes>
    suspend fun createPropose(
        gatheringId: Long?,
        whereTag: String,
        whenTag: String,
        whatTag: String
    ): Result<Proposal>
    suspend fun updateGathering(
        gatheringId: Long,
        intervalDays: Int
    ) : Result<Unit>
}
