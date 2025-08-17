package com.plottwist.core.data.gathering.repository

import com.plottwist.core.data.gathering.JoinGatheringDataSource
import com.plottwist.core.domain.gathering.JoinGatheringRepository
import javax.inject.Inject

class JoinGatheringRepositoryImpl @Inject constructor(
    private val dataSource: JoinGatheringDataSource
) :JoinGatheringRepository{
    override suspend fun joinGathering(gatheringId: Long): Result<Long> {
        try {
            val response = dataSource.joinGathering(gatheringId)
            return if (response.success) {
                val id = response.data?.id
                if (id != null) Result.success(id)
                else Result.failure(Exception("ID 없음"))
            } else {
                Result.failure(Exception(response.meta?.errorMessage ?: "알 수 없는 에러"))
            }
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getGatheringName(gatheringId: Long): Result<String> {
        try {
            val response = dataSource.getGatheringName(gatheringId)
            return if (response.success) {
                val name = response.data?.gatheringName
                if (name != null) Result.success(name)
                else Result.failure(Exception("이름 없음"))
            } else {
                Result.failure(Exception(response.meta?.errorMessage ?: "알 수 없는 에러"))
            }
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
