package com.plottwist.create_gathering.model

import com.plottwist.core.domain.gathering.model.GatheringTagModel
import com.plottwist.core.domain.gathering.model.TagCategoryModel

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

fun TagCategoryModel.toPresentation() : TagCategory {
    return TagCategory(
        categoryName = this.categoryName,
        tags = this.tags.map { it.toPresentation() }
    )
}
