package com.plottwist.core.data.gathering.mapper

import com.plottwist.core.domain.gathering.model.GatheringTagModel
import com.plottwist.core.domain.gathering.model.TagCategoryModel
import com.plottwist.core.network.model.gathering.GatheringTag
import com.plottwist.core.network.model.gathering.TagCategory


fun GatheringTag.toDomain(): GatheringTagModel {
    return GatheringTagModel(
        id = this.id,
        name = this.name
    )
}

// TagCategoryDTO (network) → TagCategoryModel (domain)
fun TagCategory.toDomain(): TagCategoryModel {
    return TagCategoryModel(
        categoryName = this.categoryName,
        tags = this.tags.map { it.toDomain() }
    )
}
