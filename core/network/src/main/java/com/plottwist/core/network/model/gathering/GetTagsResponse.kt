package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class GetTagsResponse(
    val success: Boolean,
    val data: TagsData,
    val meta: Meta?
)
@Serializable
data class TagsData(
    val categories: List<TagCategory>
)
@Serializable
data class TagCategory(
    val categoryName: String,
    val tags: List<GatheringTag>
)
@Serializable
data class GatheringTag(
    val id: Int,
    val name: String
)

@Serializable
data class GetPurposesResponse(
    val success: Boolean,
    val data: PurposeData,
    val meta: Meta?
)

@Serializable
data class PurposeData(
    val whenTags: List<String>,
    val whereTags: List<String>,
    val whatTags: List<String>
)

@Serializable
data class CreateProposeResponse(
    val success: Boolean,
    val data: ProposalResponse,
    val meta: Meta?
)

@Serializable
data class ProposalResponse(
    val proposalId: Long
)
