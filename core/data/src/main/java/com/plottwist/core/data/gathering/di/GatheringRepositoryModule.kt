package com.plottwist.core.data.gathering.di

import com.plottwist.core.data.gathering.repository.CreateGatheringRepositoryImpl
import com.plottwist.core.data.gathering.repository.TagRepositoryImpl
import com.plottwist.core.domain.gathering.repository.CreateGatheringRepository
import com.plottwist.core.domain.gathering.repository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatheringRepositoryModule {

    @Binds
    abstract fun bindTagRepository(impl: TagRepositoryImpl): TagRepository

    @Binds
    abstract fun bindCreateGatheringRepository(impl: CreateGatheringRepositoryImpl): CreateGatheringRepository
}