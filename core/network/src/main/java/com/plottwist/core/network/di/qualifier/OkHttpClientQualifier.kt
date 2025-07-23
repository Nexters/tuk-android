package com.plottwist.core.network.di.qualifier

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AuthOkHttpClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AuthRetrofit
