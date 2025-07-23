package com.plottwist.tuk.di

import com.plottwist.core.network.config.ServerConfig
import com.plottwist.tuk.config.ServerConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun providesServerConfig(
        serverConfigImpl: ServerConfigImpl
    ): ServerConfig
}
