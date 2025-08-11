package com.plottwist.core.data.auth.di

import com.plottwist.core.domain.auth.repository.AuthRepository
import com.plottwist.core.data.auth.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDataModule {

    @Binds
    abstract fun bindLoginRepository(impl: AuthRepositoryImpl): AuthRepository
}