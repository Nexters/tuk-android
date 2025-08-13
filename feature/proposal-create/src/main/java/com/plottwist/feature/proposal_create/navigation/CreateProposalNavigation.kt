package com.plottwist.feature.proposal_create.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.extension.popSlideInVertically
import com.plottwist.core.ui.extension.popSlideOutVertically
import com.plottwist.core.ui.extension.slideInVertically
import com.plottwist.core.ui.extension.slideOutVertically
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.CreateProposalScreen


fun NavController.navigateToCreateProposal(
    gatheringId: Long?,
    gatheringName: String?,
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.CreateProposal(gatheringId, gatheringName,whereLabel, whenLabel, whatLabel),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.createProposalNavGraph(
    onBack: () -> Unit,
    navigateToSelectGatheringScreen: (Long?) -> Unit,
    navigateToCompleteProposeScreen: (String) -> Unit,
) {
    composable<Route.CreateProposal> (
        enterTransition = { slideInVertically() },
        exitTransition = { null },
        popEnterTransition = { null },
        popExitTransition = { popSlideOutVertically() }
    ) { backStackEntry ->
        CreateProposalScreen(
            onBack = onBack,
            navigateToSelectGatheringScreen = navigateToSelectGatheringScreen,
            navigateToCompleteProposeScreen = navigateToCompleteProposeScreen,
            backStackEntry = backStackEntry,
        )
    }
}
