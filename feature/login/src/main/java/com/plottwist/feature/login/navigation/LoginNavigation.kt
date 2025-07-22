package com.plottwist.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.login.LoginScreen


fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.Login, navOptions = navOptions)
}

fun NavGraphBuilder.loginNavGraph() {
    composable<Route.Login> {
        LoginScreen()
    }
}
