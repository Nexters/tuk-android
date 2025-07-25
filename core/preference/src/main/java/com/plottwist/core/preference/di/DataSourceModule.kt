package com.plottwist.core.preference.di

import com.plottwist.core.preference.datasource.AuthDataSource
import com.plottwist.core.preference.datasource.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideAuthDataSource(tokenLocalDataSourceImpl: AuthDataSourceImpl): AuthDataSource
}