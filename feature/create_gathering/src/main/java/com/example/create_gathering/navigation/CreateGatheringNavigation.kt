package com.example.create_gathering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.create_gathering.CreateGatheringScreen
import com.plottwist.core.ui.navigation.Route

fun NavGraphBuilder.createGatheringNavGraph(
    onSubmit: () -> Unit
) {
    composable<Route.CreateGathering> {
        CreateGatheringScreen(
            onSubmit = onSubmit
        )
    }
}

fun NavController.navigateToCreateGathering() {
    this.navigate(Route.CreateGathering)
}