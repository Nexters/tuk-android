package com.plottwist.core.domain.model

data class Gatherings(
    val totalCount: Int = 0,
    val gatheringOverviews: List<GatheringOverviews> = emptyList()
)

data class GatheringOverviews(
    val gatheringId: Long,
    val gatheringName: String,
    val lastPushRelativeTime: String
)

data class GatheringDetail(
    val gatheringId: Long = 0,
    val gatheringName: String = "",
    val lastPushRelativeTime: String = "",
    val gatheringIntervalDays: Long = 0,
    val sentProposalCount: Int = 0,
    val receivedProposalCount: Int = 0,
    val members: List<GatheringMember> = emptyList(),
    val isHost: Boolean = false
)

data class GatheringMember(
    val memberId: Long = 0,
    val memberName: String = "",
    val isHost: Boolean = false,
    val isMe: Boolean = false
)

data class Purposes(
    val whenTags: List<String> = emptyList(),
    val whereTags: List<String> = emptyList(),
    val whatTags: List<String> = emptyList()
)

data class Proposal(
    val proposalId : Long
)
