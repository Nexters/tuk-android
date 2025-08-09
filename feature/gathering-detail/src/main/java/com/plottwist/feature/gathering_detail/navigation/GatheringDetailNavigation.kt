package com.plottwist.feature.gathering_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.extension.slideIn
import com.plottwist.core.ui.extension.slideOut
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.gathering_detail.GatheringDetailScreen


fun NavController.navigateToGatheringDetail(
    gatheringId: Long,
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.GatheringDetail(gatheringId), navOptions = navOptions)
}

fun NavGraphBuilder.gatheringDetailNavGraph(
    onBack: () -> Unit,
    navigateToWebViewScreen: (String) -> Unit,
    navigateToInviteGathering: (String) -> Unit,
    navigateToCreateGatheringProposal: (Long, String) -> Unit
) {
    composable<Route.GatheringDetail> (
        enterTransition = {
            slideIn()
        },
        exitTransition = {
            slideOut()
        }
    ) {
        GatheringDetailScreen(
            onBack = onBack,
            navigateToWebViewScreen = navigateToWebViewScreen,
            navigateToInviteGathering = navigateToInviteGathering,
            navigateToCreateGatheringProposal = navigateToCreateGatheringProposal
        )
    }
}
