package com.plottwist.core.domain.gathering.model

data class TagCategoryModel(
    val categoryName: String,
    val tags: List<GatheringTagModel>
)

data class GatheringTagModel(
    val id:Int,
    val name:String
)
