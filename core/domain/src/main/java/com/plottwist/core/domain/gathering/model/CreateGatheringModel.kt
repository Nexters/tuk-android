package com.plottwist.core.domain.gathering.model

data class CreateGatheringModel(
    val name: String,
    val intervalDays: Int,
    val tagIds: List<Int>
)
