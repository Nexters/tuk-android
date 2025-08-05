package com.plottwist.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.extension.popSlideInVertically
import com.plottwist.core.ui.extension.popSlideOutVertically
import com.plottwist.core.ui.extension.slideInVertically
import com.plottwist.core.ui.extension.slideOutVertically
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.onboarding.OnboardingNameScreen


fun NavController.navigateToOnboardingName(
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.OnboardingName,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.onboardingNameNavGraph(
    onBack: () -> Unit
) {
    composable<Route.OnboardingName> (
        enterTransition = { slideInVertically() },
        exitTransition = { slideOutVertically() },
        popEnterTransition = { popSlideInVertically() },
        popExitTransition = { popSlideOutVertically() }
    ) {
        OnboardingNameScreen(
            onBack = onBack
        )
    }
}
