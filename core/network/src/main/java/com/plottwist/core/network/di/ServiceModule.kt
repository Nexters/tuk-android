package com.plottwist.core.network.di

import com.plottwist.core.network.di.qualifier.AuthRetrofit
import com.plottwist.core.network.di.qualifier.TukRetrofit
import com.plottwist.core.network.service.AuthApiService
import com.plottwist.core.network.service.TukApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesAuthRetrofitClient(
        @AuthRetrofit retrofit: Retrofit
    ): AuthApiService {
        return retrofit
            .create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesTukRetrofitClient(
        @TukRetrofit retrofit: Retrofit
    ): TukApiService {
        return retrofit
            .create(TukApiService::class.java)
    }
}
