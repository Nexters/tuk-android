package com.plottwist.core.data.gathering.di

import com.plottwist.core.data.auth.repository.LoginRepositoryImpl
import com.plottwist.core.data.gathering.repository.TagRepositoryImpl
import com.plottwist.core.domain.auth.repository.LoginRepository
import com.plottwist.core.domain.gathering.repository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TagDataModule {

    @Binds
    abstract fun bindTagRepository(impl: TagRepositoryImpl):TagRepository
}