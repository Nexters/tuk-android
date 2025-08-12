package com.plottwist.core.data.gathering

import com.plottwist.core.network.model.auth.JoinGatheringResponse
import com.plottwist.core.network.service.AuthApiService
import com.plottwist.core.network.service.TukApiService
import javax.inject.Inject


interface JoinGatheringDataSource{
    suspend fun joinGathering(gatheringId: Long):JoinGatheringResponse
}

class JoinGatheringDataSourceImpl @Inject constructor(
    private val api:TukApiService
): JoinGatheringDataSource{

    override suspend fun joinGathering(gatheringId: Long): JoinGatheringResponse {
        return api.joinGathering(gatheringId)
    }

}
