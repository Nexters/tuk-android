package com.plottwist.core.data.mapper

import com.plottwist.core.domain.model.GatheringDetail
import com.plottwist.core.domain.model.GatheringMember
import com.plottwist.core.domain.model.GatheringOverviews
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.domain.model.Proposal
import com.plottwist.core.domain.model.Purposes
import com.plottwist.core.network.model.gathering.CreateProposeResponse
import com.plottwist.core.network.model.gathering.GatheringDetailData
import com.plottwist.core.network.model.gathering.GatheringMemberData
import com.plottwist.core.network.model.gathering.GatheringOverviewsData
import com.plottwist.core.network.model.gathering.GatheringsData
import com.plottwist.core.network.model.gathering.GetPurposesResponse

fun GatheringsData.toDomainModel() : Gatherings {
    return Gatherings(
        totalCount = this.totalCount,
        gatheringOverviews = this.gatheringOverviews.map {
            it.toDomainModel()
        }
    )
}

fun GatheringOverviewsData.toDomainModel() : GatheringOverviews {
    return GatheringOverviews(
        gatheringId = this.gatheringId,
        gatheringName = this.gatheringName,
        lastPushRelativeTime = this.lastPushRelativeTime
    )
}

fun GatheringDetailData.toDomainModel() : GatheringDetail {
    return GatheringDetail(
        gatheringId = this.gatheringId,
        gatheringName = this.gatheringName,
        lastPushRelativeTime = this.lastPushRelativeTime,
        gatheringIntervalDays = this.gatheringIntervalDays,
        sentProposalCount = this.sentProposalCount,
        receivedProposalCount = this.receivedProposalCount,
        members = this.members.map { it.toDomainModel() },
        isHost = this.isHost
    )
}

fun GatheringMemberData.toDomainModel() : GatheringMember {
    return GatheringMember(
        memberId = this.memberId,
        memberName = this.memberName,
        isHost = this.isHost,
        isMe = this.isMe
    )
}

fun GetPurposesResponse.toDomainModel() : Purposes {
    return Purposes(
        whatTags = this.data.whatTags,
        whereTags = this.data.whereTags,
        whenTags = this.data.whenTags
    )
}

fun CreateProposeResponse.toDomainModel() : Proposal {
    return Proposal(
        proposalId = this.data.proposalId
    )
}
