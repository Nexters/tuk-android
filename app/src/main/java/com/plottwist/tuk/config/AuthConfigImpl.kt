package com.plottwist.tuk.config

import com.plottwist.core.auth.provider.config.AuthConfig
import com.plottwist.tuk.BuildConfig
import javax.inject.Inject

class AuthConfigImpl @Inject constructor() : AuthConfig {
    override val googleClientId: String
        get() = BuildConfig.GOOGLE_CLIENT_ID
}
