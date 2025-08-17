package com.plottwist.core.auth.provider.google

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.plottwist.core.auth.model.AuthError
import com.plottwist.core.auth.model.AuthSuccess
import com.plottwist.core.auth.provider.AuthProvider
import com.plottwist.core.auth.provider.config.AuthConfig
import javax.inject.Inject

class GoogleAuthProvider @Inject constructor(
    private val authConfig: AuthConfig,
    private val credentialManager: CredentialManager
) : AuthProvider {
    override suspend fun login(
        context: Context,
        onSuccess: (AuthSuccess) -> Unit,
        onError: (AuthError) -> Unit,
        nonce: String
    ) {
        val signInWithGoogleOption = createGoogleSignInWithGoogleOption(authConfig.googleClientId)

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        val result = try {
            credentialManager.getCredential(
                context = context,
                request = request
            )
        } catch (e: GetCredentialCancellationException) {
            onError(AuthError("Cancelled by User"))
            return
        } catch (e: Exception) {
            onError(AuthError("Unknown Error"))
            return
        }

        handleSignInWithGoogleOption(
            result = result,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    private fun createGoogleSignInWithGoogleOption(
        webClientId: String,
        nonce: String = ""
    ): GetSignInWithGoogleOption {
        val signInWithGoogleOption: GetSignInWithGoogleOption = GetSignInWithGoogleOption.Builder(
            serverClientId = webClientId
        ).setNonce(nonce)
            .build()

        return signInWithGoogleOption
    }

    private fun handleSignInWithGoogleOption(
        result: GetCredentialResponse,
        onSuccess: (AuthSuccess) -> Unit,
        onError: (AuthError) -> Unit,
    ) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        onSuccess(AuthSuccess(googleIdTokenCredential.idToken))
                    } catch (e: GoogleIdTokenParsingException) {
                        onError(AuthError(e.message ?: "invalid google id token response"))
                    }
                }
                else {
                    onError(AuthError("unrecognized credential type here"))
                }
            }

            else -> {
                onError(AuthError("unrecognized credential type here"))
            }
        }
    }
}
