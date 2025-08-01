package com.plottwist.core.data.mapper

import com.plottwist.core.domain.model.GatheringOverviews
import com.plottwist.core.domain.model.Gatherings
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
