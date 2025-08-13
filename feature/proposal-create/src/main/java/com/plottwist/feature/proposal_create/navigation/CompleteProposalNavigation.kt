package com.plottwist.feature.proposal_create.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.complete.CompleteProposeScreen


fun NavGraphBuilder.completeProposalNavGraph(
    navigateToHome: () -> Unit
) {
    composable<Route.CompleteProposal>{
        CompleteProposeScreen(
            navigateToHome = navigateToHome
        )
    }
}

fun NavController.navigateToCompleteProposal(
    encodedUrl: String,
    navOptions: NavOptions? = null
) {
    this.navigate(Route.CompleteProposal(encodedUrl), navOptions)
}
