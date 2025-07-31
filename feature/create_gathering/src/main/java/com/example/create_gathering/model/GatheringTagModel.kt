package com.example.create_gathering.model

import com.plottwist.core.domain.gathering.model.GatheringTagModel

data class TagCategory(
    val categoryName: String,
    val tags: List<GatheringTag>
)
data class GatheringTag(
    val id:Int,
    val name:String
)
fun GatheringTagModel.toPresentation(): GatheringTag {
    return GatheringTag(id = this.id, name = this.name)
}