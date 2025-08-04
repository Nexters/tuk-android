package com.plottwist.core.domain.gathering

interface JoinGatheringRepository {
    suspend fun joinGathering(gatheringId: Long): Result<Long>
}