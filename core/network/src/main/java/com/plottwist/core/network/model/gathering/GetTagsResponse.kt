package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class GetTagsResponse(
    val success: Boolean,
    val data: TagsData,
    val meta: Meta
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

