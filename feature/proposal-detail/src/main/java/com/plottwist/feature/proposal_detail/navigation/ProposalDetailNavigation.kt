package com.plottwist.feature.proposal_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_detail.ProposalDetailScreen

fun NavController.navigateToProposalDetail(
    proposalId: Long,
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.ProposalDetail(proposalId), navOptions = navOptions)
}

fun NavGraphBuilder.proposalDetailNavGraph(
    onBack: () -> Unit,
) {
    composable<Route.ProposalDetail> (
        deepLinks = listOf(
            navDeepLink<Route.ProposalDetail>(
                basePath = "https://tuk/proposal-detail"
            ),
            navDeepLink<Route.ProposalDetail>(
                basePath = "tuk-app://tuk/proposal-detail"
            )
        ),
    ) {
        ProposalDetailScreen(
            onBack = onBack
        )
    }
}
