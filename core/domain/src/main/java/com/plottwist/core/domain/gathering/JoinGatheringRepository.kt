package com.plottwist.core.domain.gathering

interface JoinGatheringRepository {
    suspend fun joinGathering(gatheringId: Long): Result<Long>
    suspend fun getGatheringName(gatheringId: Long): Result<String>
}
