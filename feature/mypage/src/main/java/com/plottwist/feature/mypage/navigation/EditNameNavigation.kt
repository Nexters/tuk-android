package com.plottwist.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.mypage.edit_name.EditNameScreen
import com.plottwist.feature.mypage.notification.NotificationSettingScreen

fun NavController.navigateToEditName(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.EditName, navOptions = navOptions)
}

fun NavGraphBuilder.editNameNavGraph(
    onBack: () -> Unit
) {
    composable<Route.EditName> {
        EditNameScreen(onBack = onBack)
    }
}
