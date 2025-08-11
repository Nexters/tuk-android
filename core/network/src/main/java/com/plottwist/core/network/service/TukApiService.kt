package com.plottwist.core.network.service

import com.plottwist.core.network.model.gathering.CreateGatheringRequest
import com.plottwist.core.network.model.gathering.CreateGatheringResponse
import com.plottwist.core.network.model.gathering.CreateProposeRequest
import com.plottwist.core.network.model.gathering.CreateProposeResponse
import com.plottwist.core.network.model.gathering.GatheringDetailResponse
import com.plottwist.core.network.model.gathering.GatheringsResponse
import com.plottwist.core.network.model.gathering.GetPurposesResponse
import com.plottwist.core.network.model.gathering.GetTagsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TukApiService {
    @GET("/api/v1/gatherings")
    suspend fun getGatherings(): GatheringsResponse

    @POST("/api/v1/gatherings")
    suspend fun createGathering(
        @Body body:CreateGatheringRequest
    ):  Response<CreateGatheringResponse>

    @GET("/api/v1/tags")
    suspend fun getGatheringTags(): GetTagsResponse

    @GET("/api/v1/gatherings/{gatheringId}/members")
    suspend fun getGatheringDetail(
        @Path("gatheringId") gatheringId: Long
    ): GatheringDetailResponse

    @GET("/api/v1/purposes")
    suspend fun getPurposes(): GetPurposesResponse

    @POST("/api/v1/gatherings/{gatheringId}/proposals")
    suspend fun createPropose(
        @Path("gatheringId") gatheringId: Long?,
        @Body createProposeRequest : CreateProposeRequest
    ): CreateProposeResponse
}
