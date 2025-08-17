package com.plottwist.core.network.model.onboarding

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable

@Serializable
data class UpdateOnboardingInfoResponse(
    val success: Boolean,
    val data: UpdateOnboardingInfoData,
    val meta: Meta?
)

@Serializable
data class UpdateOnboardingInfoData(
    val memberId: Long
)
