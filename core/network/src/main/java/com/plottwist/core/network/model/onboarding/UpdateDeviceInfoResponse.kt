package com.plottwist.core.network.model.onboarding

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable

@Serializable
data class UpdateDeviceInfoResponse(
    val success: Boolean,
    val meta: Meta?
)
