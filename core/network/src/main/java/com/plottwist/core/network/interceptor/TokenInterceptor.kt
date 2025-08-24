package com.plottwist.core.network.interceptor

import com.plottwist.core.network.TokenProvider
import com.plottwist.core.network.model.auth.TokenRequest
import com.plottwist.core.network.service.AuthApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenProvider: TokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().apply {
            runBlocking {
                val token = tokenProvider.getAccessToken()
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
        }

        val response = chain.proceed(newRequest.build())

        when (response.code) {
            HttpURLConnection.HTTP_OK -> {
                val newAccessToken: String = response.header("Authorization", null) ?: return response
                CoroutineScope(Dispatchers.IO).launch {
                    val existedAccessToken = tokenProvider.getAccessToken()
                    if (existedAccessToken != newAccessToken) {
                        tokenProvider.setAccessToken(newAccessToken)
                    }
                }
            }
            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                val retryRequest = chain.request().newBuilder().apply {
                    runBlocking {
                        val accessToken = tokenProvider.getAccessToken()
                        val refreshToken = tokenProvider.getRefreshToken()
                        if(accessToken.isNullOrEmpty() && refreshToken.isNullOrEmpty()){
                            return@runBlocking
                        }
                        refreshToken?.let {
                            try {
                                val newToken = authApiService.refreshToken(TokenRequest(it))
                                if (newToken.success) {
                                    newToken.data.let { token ->
                                        addHeader("Authorization", "Bearer ${token.accessToken}")
                                        CoroutineScope(Dispatchers.IO).launch {
                                            tokenProvider.setAccessToken(token.accessToken)
                                            tokenProvider.setRefreshToken(token.refreshToken)
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                                return@let
                            }
                        }
                    }
                }
                response.close()
                return chain.proceed(retryRequest.build())
            }
        }
        return response
    }
}
