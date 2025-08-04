package com.plottwist.core.domain.model

data class Gatherings(
    val totalCount: Int = 0,
    val gatheringOverviews: List<GatheringOverviews> = emptyList()
)

data class GatheringOverviews(
    val gatheringId: Long,
    val gatheringName: String,
    val lastNotificationRelativeTime: String
)

data class GatheringDetail(
    val gatheringId: Long = 0,
    val gatheringName: String = "",
    val lastNotificationRelativeTime: String = "",
    val sentInvitationCount: Int = 0,
    val receivedInvitationCount: Int = 0,
    val members: List<GatheringMember> = emptyList()
)

data class GatheringMember(
    val memberId: Long = 0,
    val memberName: String = ""
)
