package com.plottwist.core.data.gathering.datasource

import com.plottwist.core.network.model.gathering.GetTagsResponse
import com.plottwist.core.network.service.TukApiService
import javax.inject.Inject

interface TagRemoteDataSource {
    suspend fun getTags(): GetTagsResponse
}

class TagRemoteDataSourceImpl @Inject constructor(
    private val tukApiService: TukApiService
): TagRemoteDataSource {

    override suspend fun getTags(): GetTagsResponse {
        return tukApiService.getGatheringTags()
    }

}