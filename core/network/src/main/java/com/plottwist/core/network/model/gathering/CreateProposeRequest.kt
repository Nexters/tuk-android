package com.plottwist.core.network.model.gathering

import kotlinx.serialization.Serializable

@Serializable
data class CreateProposeRequest(
    val gatheringId: Long?,
    val purpose : CreateProposeData
)

@Serializable
data class CreateProposeData(
    val whereTag : String,
    val whenTag : String,
    val whatTag: String
)
