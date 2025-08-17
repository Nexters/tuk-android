package com.plottwist.core.auth.provider

import android.content.Context
import com.plottwist.core.auth.model.AuthError
import com.plottwist.core.auth.model.AuthSuccess

interface AuthProvider {
    suspend fun login(
        context: Context,
        onSuccess: (AuthSuccess) -> Unit,
        onError: (AuthError) -> Unit,
        nonce: String = ""
    )
    fun isLoggedIn() : Boolean
    fun logout()
}
