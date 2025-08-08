package com.plottwist.core.data.mapper

import com.plottwist.core.domain.model.GatheringDetail
import com.plottwist.core.domain.model.GatheringMember
import com.plottwist.core.domain.model.GatheringOverviews
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.network.model.gathering.GatheringDetailData
import com.plottwist.core.network.model.gathering.GatheringMemberData
import com.plottwist.core.network.model.gathering.GatheringOverviewsData
import com.plottwist.core.network.model.gathering.GatheringsData

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
        lastNotificationRelativeTime = this.lastNotificationRelativeTime
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
        members = this.members.map { it.toDomainModel() }
    )
}

fun GatheringMemberData.toDomainModel() : GatheringMember {
    return GatheringMember(
        memberId = this.memberId,
        memberName = this.memberName
    )
}
