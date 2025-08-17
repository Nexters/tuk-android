package com.plottwist.join_gathering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.plottwist.core.ui.extension.popSlideOutVertically
import com.plottwist.core.ui.extension.slideInVertically
import com.plottwist.core.ui.navigation.Route
import com.plottwist.join_gathering.JoinGatheringScreen

fun NavController.navigateToJoinGathering(
    gatheringId: Long,
    gatheringName: String,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.CreateGatheringProposal(gatheringId, gatheringName),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.createJoinGatheringNavGraph(
    onBack: () -> Unit,
    onNavigateToGatheringDetail : (Long) -> Unit,
) {
    composable<Route.JoinGathering> (
        deepLinks = listOf(
            navDeepLink<Route.JoinGathering>(
                basePath = "https://tuk.kr/join-gathering"
            ),
            navDeepLink<Route.JoinGathering>(
                basePath = "tuk-app://tuk/join-gathering"
            )
        ),
        enterTransition = { slideInVertically() },
        exitTransition = { null },
        popEnterTransition = { null },
        popExitTransition = { popSlideOutVertically() }
    ) {
        JoinGatheringScreen(
            onCloseClicked = onBack,
            onNavigateToGatheringDetail = onNavigateToGatheringDetail
        )
    }
}
