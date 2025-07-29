package com.plottwist.core.data.gathering.di

import com.plottwist.core.data.gathering.datasource.CreateGatheringRemoteDataSource
import com.plottwist.core.data.gathering.datasource.CreateGatheringRemoteDataSourceImpl
import com.plottwist.core.data.gathering.datasource.TagRemoteDataSource
import com.plottwist.core.data.gathering.datasource.TagRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatheringDataSourceModule {

    @Binds
    abstract fun bindCreateGatheringDataSource(
        impl: CreateGatheringRemoteDataSourceImpl
    ): CreateGatheringRemoteDataSource

    @Binds
    abstract fun bindTagDataSource(
        impl: TagRemoteDataSourceImpl
    ): TagRemoteDataSource
}