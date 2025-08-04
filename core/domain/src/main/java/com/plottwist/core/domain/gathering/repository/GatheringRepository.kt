package com.plottwist.core.domain.gathering.repository

import com.plottwist.core.domain.model.GatheringDetail
import com.plottwist.core.domain.model.Gatherings

interface GatheringRepository {
    suspend fun getGatherings(): Result<Gatherings>
    suspend fun getGatheringDetail(gatheringId: Long): Result<GatheringDetail>
}
