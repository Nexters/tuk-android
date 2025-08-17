package com.plottwist.core.data.gathering.di

import com.plottwist.core.data.gathering.repository.GatheringRepositoryImpl
import com.plottwist.core.domain.gathering.repository.GatheringRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class GatheringDataModule {

    @Binds
    abstract fun bindGatheringRepository(
        impl: GatheringRepositoryImpl
    ): GatheringRepository
}
