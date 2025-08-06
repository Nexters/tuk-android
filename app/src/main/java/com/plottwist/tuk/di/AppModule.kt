package com.plottwist.tuk.di

import com.plottwist.core.auth.provider.config.AuthConfig
import com.plottwist.core.network.config.ServerConfig
import com.plottwist.core.weburl.WebUrlConfig
import com.plottwist.tuk.config.AuthConfigImpl
import com.plottwist.tuk.config.ServerConfigImpl
import com.plottwist.tuk.config.WebUrlConfigImpl
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

    @Binds
    @Singleton
    fun providerAuthConfig(
        authConfigImpl: AuthConfigImpl
    ): AuthConfig

    @Binds
    @Singleton
    fun providerWebUrlConfig(
        webUrlConfigImpl: WebUrlConfigImpl
    ): WebUrlConfig
}
