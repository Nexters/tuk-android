package com.plottwist.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navigateToLoginScreen: () -> Unit,
    navigateToMyPageScreen: () -> Unit,
    navigateToCreateGathering: () -> Unit
) {
    composable<Route.Home> {
        HomeScreen(
            navigateToLoginScreen = navigateToLoginScreen,
            navigateToMyPageScreen = navigateToMyPageScreen,
            navigateToCreateGathering = navigateToCreateGathering
        )
    }
}
