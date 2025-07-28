package com.plottwist.core.domain.gathering.repository

import com.plottwist.core.domain.gathering.model.TagCategoryModel
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getGatheringTags(): Flow<Result<List<TagCategoryModel>>>
}