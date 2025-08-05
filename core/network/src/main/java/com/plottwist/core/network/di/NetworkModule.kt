package com.plottwist.core.network.di

import com.plottwist.core.network.config.ServerConfig
import com.plottwist.core.network.di.qualifier.AuthOkHttpClient
import com.plottwist.core.network.di.qualifier.AuthRetrofit
import com.plottwist.core.network.di.qualifier.TukOkHttpClient
import com.plottwist.core.network.di.qualifier.TukRetrofit
import com.plottwist.core.network.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import com.plottwist.core.network.service.OnboardingService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @AuthOkHttpClient okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        serverConfig: ServerConfig
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(serverConfig.baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }

    @TukRetrofit
    @Provides
    @Singleton
    fun provideTukRetrofit(
        @TukOkHttpClient okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        serverConfig: ServerConfig
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(serverConfig.baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }

    @AuthOkHttpClient
    @Provides
    @Singleton
    fun providesAuthOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            connectTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            writeTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        }.addInterceptor(httpLoggingInterceptor)
         .build()
    }

    @TukOkHttpClient
    @Provides
    @Singleton
    fun providesTukOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            connectTimeout(timeout = 10, unit = TimeUnit.SECONDS)
            writeTimeout(timeout = 15, unit = TimeUnit.SECONDS)
        }.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRequestHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideConverterFactory(
        json: Json,
    ): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideOnboardingService(@TukRetrofit retrofit: Retrofit): OnboardingService {
        return retrofit.create(OnboardingService::class.java)
    }
}
