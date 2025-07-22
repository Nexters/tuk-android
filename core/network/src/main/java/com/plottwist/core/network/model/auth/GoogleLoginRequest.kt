package com.plottwist.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class GoogleLoginRequest(
    val idToken: String,
    val deviceInfo: DeviceInfo
)

@Serializable
data class DeviceInfo(
    val deviceId: String,
    val deviceType: String,
    val appVersion: String = "",
    val osVersion: String = ""
)
