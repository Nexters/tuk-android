package com.plottwist.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.extension.popSlideInVertically
import com.plottwist.core.ui.extension.popSlideOutVertically
import com.plottwist.core.ui.extension.slideInVertically
import com.plottwist.core.ui.extension.slideOutVertically
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.login.LoginScreen


fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.Login, navOptions = navOptions)
}

fun NavGraphBuilder.loginNavGraph(
    onBack: () -> Unit
) {
    composable<Route.Login> (
        enterTransition = { slideInVertically() },
        exitTransition = { slideOutVertically() },
        popEnterTransition = { popSlideInVertically() },
        popExitTransition = { popSlideOutVertically() }
    ) {
        LoginScreen(onBack)
    }
}
