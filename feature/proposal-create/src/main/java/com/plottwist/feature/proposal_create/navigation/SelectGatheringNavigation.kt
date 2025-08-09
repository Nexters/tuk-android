package com.plottwist.feature.proposal_create.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.extension.slideIn
import com.plottwist.core.ui.extension.slideOut
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
        enterTransition = { slideIn() },
        popExitTransition = { slideOut() }
    ) {
        SelectGatheringScreen(
            onBack = onBack,
            backToCreateProposal = backToCreateProposal
        )
    }
}
