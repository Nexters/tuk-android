package com.plottwist.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navigateToLoginScreen: () -> Unit,
    navigateToMyPageScreen: () -> Unit,
    navigateToCreateGathering: () -> Unit,
    navigateToGatheringDetail: (Long) -> Unit,
    navigateToCreateProposal: (whereLabel: String, whenLabel: String, whatLabel: String) -> Unit,
    navigateToWebView: (String) -> Unit,
) {
    composable<Route.Home> {
        HomeScreen(
            navigateToLoginScreen = navigateToLoginScreen,
            navigateToMyPageScreen = navigateToMyPageScreen,
            navigateToCreateGathering = navigateToCreateGathering,
            navigateToGatheringDetail = navigateToGatheringDetail,
            navigateToCreateProposal = navigateToCreateProposal,
            navigateToWebView = navigateToWebView
        )
    }
}
fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.Home, navOptions = navOptions)
}
