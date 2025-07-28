package com.plottwist.core.data.gathering.repository

import com.plottwist.core.data.gathering.datasource.TagRemoteDataSourceImpl
import com.plottwist.core.data.gathering.mapper.toDomain
import com.plottwist.core.domain.gathering.model.TagCategoryModel
import com.plottwist.core.domain.gathering.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagRemoteDataSource: TagRemoteDataSourceImpl
) : TagRepository {

    override fun getGatheringTags(): Flow<Result<List<TagCategoryModel>>> = flow {
        try {
            val response = tagRemoteDataSource.getTags()
            if (response.success) {
                val domainCategories = response.data.categories.map { it.toDomain() }
                emit(Result.success(domainCategories))
            } else {
                emit(Result.failure(Exception(response.meta.errorMessage)))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}