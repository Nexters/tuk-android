package com.plottwist.core.network.model.gathering

import com.plottwist.core.network.model.auth.Meta
import kotlinx.serialization.Serializable


@Serializable
data class GatheringDetailResponse(
    val success: Boolean,
    val data: GatheringDetailData,
    val meta: Meta?
)

@Serializable
data class GatheringDetailData(
    val gatheringId: Long,
    val gatheringName: String,
    val lastNotificationRelativeTime: String,
    val sentInvitationCount: Int,
    val receivedInvitationCount: Int,
    val members: List<GatheringMemberData>
)

@Serializable
data class GatheringMemberData(
    val memberId: Long,
    val memberName: String
)
