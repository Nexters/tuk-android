package com.plottwist.core.data.gathering

import com.plottwist.core.network.model.auth.GetGatheringNameResponse
import com.plottwist.core.network.model.auth.JoinGatheringResponse
import com.plottwist.core.network.service.AuthApiService
import com.plottwist.core.network.service.TukApiService
import javax.inject.Inject


interface JoinGatheringDataSource{
    suspend fun joinGathering(gatheringId: Long):JoinGatheringResponse
    suspend fun getGatheringName(gatheringId: Long):GetGatheringNameResponse
}

class JoinGatheringDataSourceImpl @Inject constructor(
    private val api:TukApiService
): JoinGatheringDataSource{

    override suspend fun joinGathering(gatheringId: Long): JoinGatheringResponse {
        return api.joinGathering(gatheringId)
    }

    override suspend fun getGatheringName(gatheringId: Long): GetGatheringNameResponse {
        return api.getGatheringName(gatheringId)
    }
}
