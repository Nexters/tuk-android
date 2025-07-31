package com.plottwist.core.domain.gathering.usecase

import com.plottwist.core.domain.gathering.model.TagCategoryModel
import com.plottwist.core.domain.gathering.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGatheringTagsUseCase @Inject constructor(
    private val tagRepository: TagRepository
) {

    operator fun invoke(): Flow<Result<List<TagCategoryModel>>> {
        return tagRepository.getGatheringTags()
    }
}