package com.plottwist.tuk.config

import com.plottwist.core.network.config.ServerConfig
import com.plottwist.tuk.BuildConfig
import javax.inject.Inject

class ServerConfigImpl @Inject constructor() : ServerConfig {
    override val baseUrl: String
        get() = BuildConfig.TUK_BASE_URL
}
