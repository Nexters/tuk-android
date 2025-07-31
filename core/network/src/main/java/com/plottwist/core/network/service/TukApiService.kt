package com.plottwist.core.network.service

import com.plottwist.core.network.model.gathering.CreateGatheringRequest
import com.plottwist.core.network.model.gathering.CreateGatheringResponse
import com.plottwist.core.network.model.gathering.GetTagsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TukApiService {

    @POST("/api/v1/gatherings")
    suspend fun createGathering(
        @Body body:CreateGatheringRequest
    ):  Response<CreateGatheringResponse>

    @GET("/api/v1/tags")
    suspend fun getGatheringTags():GetTagsResponse

}