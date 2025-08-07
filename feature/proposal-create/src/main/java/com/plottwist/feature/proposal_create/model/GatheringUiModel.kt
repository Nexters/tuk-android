package com.plottwist.feature.proposal_create.model

import com.plottwist.core.domain.model.GatheringOverviews

data class GatheringUiModel(
    val id: Long,
    val name: String,
    val lastNotificationRelativeTime: String = "",
    val selected: Boolean = false
)

fun GatheringOverviews.toSelectGatheringUiModel() =
    GatheringUiModel(
        id = gatheringId,
        name = gatheringName,
        lastNotificationRelativeTime = lastNotificationRelativeTime
    )
