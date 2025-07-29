package com.plottwist.core.data.gathering.datasource

import com.plottwist.core.network.model.gathering.CreateGatheringRequest
import com.plottwist.core.network.service.TukApiService
import javax.inject.Inject


interface CreateGatheringRemoteDataSource {
    suspend fun createGathering(request: CreateGatheringRequest): Result<Int>
}

class CreateGatheringRemoteDataSourceImpl @Inject constructor(
    private val apiService: TukApiService
) : CreateGatheringRemoteDataSource {

    override suspend fun createGathering(request: CreateGatheringRequest): Result<Int> {
        return try {
            val response = apiService.createGathering(request)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.success && body.data != null) {
                    Result.success(body.data!!.gatheringId)
                } else {
                    Result.failure(Exception(body?.meta?.errorMessage))
                }
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}