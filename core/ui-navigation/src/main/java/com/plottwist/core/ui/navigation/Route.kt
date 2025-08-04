package com.plottwist.core.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object Home: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object MyPage: Route

    @Serializable
    data object CreateGathering: Route

    @Serializable
    data object InviteGathering: Route

    @Serializable
    data class GatheringDetail(
        val gatheringId: Long
    ): Route

    @Serializable
    data class CreateProposal(
        val whereLabel: String,
        val whenLabel: String,
        val whatLabel: String
    )
}
