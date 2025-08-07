package com.plottwist.create_gathering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.plottwist.create_gathering.CreateGatheringScreen
import com.plottwist.core.ui.navigation.Route

fun NavGraphBuilder.createGatheringNavGraph(
    navigateToHomeScreen: () -> Unit
) {
    composable<Route.CreateGathering> {
        CreateGatheringScreen(
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}

fun NavController.navigateToCreateGathering() {
    this.navigate(Route.CreateGathering)
}
