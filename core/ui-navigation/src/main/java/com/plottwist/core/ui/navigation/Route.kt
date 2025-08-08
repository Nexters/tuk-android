package com.plottwist.core.ui.navigation

import android.net.Uri
import kotlinx.serialization.Serializable

object RoutePath {

    const val PolicyBase = "policy_webview?title={title}&url={url}"

    fun Policy(title: String, url: String): String {
        val encodedTitle = Uri.encode(title)
        val encodedUrl = Uri.encode(url)
        return "policy_webview?title=$encodedTitle&url=$encodedUrl"
    }
}

sealed interface Route {

    @Serializable
    data object Home: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object MyPage: Route

    @Serializable
    data object NotificationSetting: Route

    @Serializable
    data object CreateGathering: Route

    @Serializable
    data class InviteGathering(val encodedUrl : String): Route

    @Serializable
    data class GatheringDetail(
        val gatheringId: Long
    ): Route

    @Serializable
    data class CreateProposal(
        val whereLabel: String,
        val whenLabel: String,
        val whatLabel: String
    ) : Route

    @Serializable
    data object OnboardingName : Route

    @Serializable
    data class WebView(
        val url: String
    ) : Route

    @Serializable
    data class SelectGathering(
        val gatheringId: Long?
    ) : Route

    @Serializable
    data class CreateGatheringProposal(
        val gatheringId: Long,
        val gatheringName: String
    ) : Route
}
