package com.plottwist.core.notification.di

import android.content.Context
import com.plottwist.core.notification.TukNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NotificationModule {
    @Singleton
    @Provides
    fun provideMissionMateNotificationManager(
        @ApplicationContext context: Context
    ): TukNotificationManager {
        return TukNotificationManager(context)
    }
}
