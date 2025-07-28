package com.plottwist.core.data.gathering.repository

import com.plottwist.core.data.mapper.toDomainModel
import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.network.service.TukApiService
import javax.inject.Inject

class GatheringRepositoryImpl @Inject constructor(
    private val tukApiService: TukApiService
) : GatheringRepository {
    override suspend fun getGatherings(): Result<Gatherings> {
        return try {
            Result.success(tukApiService.getGatherings().data.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
