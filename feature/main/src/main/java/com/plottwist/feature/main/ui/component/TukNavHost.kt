package com.plottwist.feature.main.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.home.navigation.homeNavGraph
import com.plottwist.feature.login.navigation.loginNavGraph
import com.plottwist.feature.login.navigation.navigateToLogin

@Composable
fun TukNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home
    ) {
        homeNavGraph(
            navigateToLoginScreen = {
                navController.navigateToLogin()
            }
        )
        loginNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}
