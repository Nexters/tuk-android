package com.plottwist.core.domain.gathering.repository

import com.plottwist.core.domain.common.BaseResponse
import com.plottwist.core.domain.gathering.model.CreateGatheringModel

interface CreateGatheringRepository {
    suspend fun createGathering(request: CreateGatheringModel): Result<Int>
}