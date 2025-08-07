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
import com.plottwist.feature.proposal_create.gathering_select.SelectGatheringScreen
import com.plottwist.feature.proposal_create.model.SelectedGatheringParam

fun NavController.navigateToSelectGathering(
    gatheringId: Long?,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.SelectGathering(gatheringId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.selectGatheringNavGraph(
    onBack: () -> Unit,
    backToCreateProposal: (SelectedGatheringParam) -> Unit
) {
    composable<Route.SelectGathering> (
        enterTransition = { slideInVertically() },
        exitTransition = { slideOutVertically() },
        popEnterTransition = { popSlideInVertically() },
        popExitTransition = { popSlideOutVertically() }
    ) {
        SelectGatheringScreen(
            onBack = onBack,
            backToCreateProposal = backToCreateProposal
        )
    }
}
