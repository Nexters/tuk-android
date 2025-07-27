package com.plottwist.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.mypage.MyPageScreen

fun NavController.navigateToMyPage(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.MyPage, navOptions = navOptions)
}

fun NavGraphBuilder.myPageNavGraph() {
    composable<Route.MyPage> {
        MyPageScreen()
    }
}
