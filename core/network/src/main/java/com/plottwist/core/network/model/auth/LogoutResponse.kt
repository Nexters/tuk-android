package com.plottwist.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class DeleteMemberResponse(
    val success: Boolean,
    val meta: Meta?
)
