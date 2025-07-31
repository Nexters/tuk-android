package com.example.invite_gathering.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.invite_gathering.InviteGatheringScreen
import com.plottwist.core.ui.navigation.Route

fun NavGraphBuilder.inviteGatheringNavGraph(

) {
    composable<Route.InviteGathering>{
        InviteGatheringScreen()
    }
}

fun NavController.navigateToInviteGathering() {
    this.navigate(Route.InviteGathering)
}