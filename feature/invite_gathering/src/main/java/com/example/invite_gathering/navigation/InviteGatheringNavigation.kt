package com.example.invite_gathering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.invite_gathering.InviteGatheringScreen
import com.plottwist.core.ui.navigation.Route

fun NavGraphBuilder.inviteGatheringNavGraph(
    onCloseClicked: () -> Unit
) {
    composable<Route.InviteGathering>{
        InviteGatheringScreen(
            onCloseClicked = onCloseClicked
        )
    }
}

fun NavController.navigateToInviteGathering() {
    this.navigate(Route.InviteGathering)
}
