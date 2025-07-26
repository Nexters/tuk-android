package com.plottwist.core.data.auth.di

import com.plottwist.core.data.auth.AuthTokenProvider
import com.plottwist.core.network.TokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthDataModule {

    @Binds
    abstract fun bindTokenProvider(impl: AuthTokenProvider): TokenProvider
}
