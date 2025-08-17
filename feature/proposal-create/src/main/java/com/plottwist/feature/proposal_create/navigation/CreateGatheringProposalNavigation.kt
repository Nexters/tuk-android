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
import com.plottwist.feature.proposal_create.gathering_proposal.CreateGatheringProposalScreen


fun NavController.navigateToCreateGatheringProposal(
    gatheringId: Long,
    gatheringName: String,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.CreateGatheringProposal(gatheringId, gatheringName),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.createGatheringProposalNavGraph(
    onBack: () -> Unit,
) {
    composable<Route.CreateGatheringProposal> (
        enterTransition = { slideInVertically() },
        exitTransition = { null },
        popEnterTransition = { null },
        popExitTransition = { popSlideOutVertically() }
    ) {
        CreateGatheringProposalScreen(
            onBack = onBack
        )
    }
}
