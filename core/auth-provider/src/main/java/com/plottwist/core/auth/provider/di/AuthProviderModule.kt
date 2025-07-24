package com.plottwist.core.auth.provider.di

import com.plottwist.core.auth.provider.AuthProvider
import com.plottwist.core.auth.provider.google.GoogleAuthProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoogleAuth

@EntryPoint
@InstallIn(ActivityComponent::class)
interface AuthProviderEntryPoint {
    fun googleAuthProvider(): GoogleAuthProvider
}

@InstallIn(SingletonComponent::class)
@Module
abstract class AuthProviderModule {
    @Binds
    @Singleton
    @GoogleAuth
    abstract fun bindsGoogleAuthProvider(
        googleAuthProvider: GoogleAuthProvider
    ): AuthProvider
}
