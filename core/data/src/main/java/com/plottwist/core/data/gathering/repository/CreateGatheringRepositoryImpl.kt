package com.plottwist.core.data.gathering.repository

import com.plottwist.core.data.gathering.datasource.CreateGatheringRemoteDataSource
import com.plottwist.core.domain.gathering.model.CreateGatheringModel
import com.plottwist.core.domain.gathering.repository.CreateGatheringRepository
import com.plottwist.core.network.model.gathering.CreateGatheringRequest
import javax.inject.Inject

class CreateGatheringRepositoryImpl @Inject constructor(
    private val remoteDataSource: CreateGatheringRemoteDataSource
) :CreateGatheringRepository{

    override suspend fun createGathering(request: CreateGatheringModel): Result<Int> {
        val createGatheringModel = CreateGatheringRequest(
            gatheringName = request.name,
            gatheringIntervalDays = request.intervalDays,
            tagIds = request.tagIds
        )
        return remoteDataSource.createGathering(createGatheringModel)
    }
}