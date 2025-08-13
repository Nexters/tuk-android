package com.plottwist.core.data.gathering.repository

import com.plottwist.core.data.mapper.toDomainModel
import com.plottwist.core.domain.gathering.repository.GatheringRepository
import com.plottwist.core.domain.model.GatheringDetail
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.domain.model.Proposal
import com.plottwist.core.domain.model.Purposes
import com.plottwist.core.network.model.gathering.CreateProposeData
import com.plottwist.core.network.model.gathering.CreateProposeRequest
import com.plottwist.core.network.model.gathering.UpdateGatheringRequest
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

    override suspend fun getGatheringDetail(gatheringId: Long): Result<GatheringDetail> {
        return try {
            Result.success(tukApiService.getGatheringDetail(gatheringId).data.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPurposes(): Result<Purposes> {
        return try {
            Result.success(tukApiService.getPurposes().toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createPropose(
        gatheringId: Long?,
        whereTag: String,
        whenTag: String,
        whatTag: String
    ): Result<Proposal> {
        try {
            val result = tukApiService.createPropose(
                gatheringId = gatheringId,
                CreateProposeRequest(
                    purpose = CreateProposeData(
                        whereTag = whereTag,
                        whenTag = whenTag,
                        whatTag = whatTag
                    )
                )
            )

            return if(result.success) {
                Result.success(result.toDomainModel())
            }else {
                Result.failure(Exception(result.meta?.errorMessage))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun updateGathering(
        gatheringId: Long,
        intervalDays: Int
    ) : Result<Unit> {
        try {
            val result = tukApiService.updateGathering(
                gatheringId = gatheringId,
                updateGatheringRequest = UpdateGatheringRequest(
                    gatheringIntervalDays = intervalDays
                )
            )

            return if(result.success) {
                Result.success(Unit)
            }else {
                Result.failure(Exception(result.meta?.errorMessage))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
