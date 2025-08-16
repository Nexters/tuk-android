package com.plottwist.core.network.model.onboarding

import kotlinx.serialization.Serializable

@Serializable
data class MemberNameRequest(
    val name: String
)
