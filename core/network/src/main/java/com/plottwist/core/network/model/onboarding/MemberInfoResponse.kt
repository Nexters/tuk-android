package com.plottwist.core.network.model.onboarding

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable

@Serializable
data class MemberInfoResponse(
    val success: Boolean,
    val data: MemberInfoData,
    val meta: Meta?
)

@Serializable
data class MemberInfoData(
    val memberId: Long,
    val email: String,
    val name: String
)
