package com.plottwist.core.data.push.di

import com.plottwist.core.data.onboarding.OnboardingRepositoryImpl
import com.plottwist.core.data.push.repository.PushRepositoryImpl
import com.plottwist.core.domain.onboarding.OnboardingRepository
import com.plottwist.core.domain.push.repository.PushRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PushRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPushRepository(
        pushRepositoryImpl: PushRepositoryImpl
    ): PushRepository
}
