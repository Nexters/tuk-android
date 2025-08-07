package com.plottwist.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.plottwist.core.ui.navigation.Route
import com.plottwist.core.ui.navigation.RoutePath
import com.plottwist.feature.mypage.MyPageScreen
import com.plottwist.feature.mypage.notification.NotificationSettingScreen
import com.plottwist.feature.mypage.policy.PolicyWebViewScreen

fun NavController.navigateToMyPage(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.MyPage, navOptions = navOptions)
}

fun NavController.navigateToNotificationSetting(
    navOptions: NavOptions? = null
) {
    this.navigate(route = Route.NotificationSetting, navOptions = navOptions)
}

fun NavController.navigateToPolicyWebView(title: String, url: String) {
    this.navigate(RoutePath.Policy(title, url))
}

fun NavGraphBuilder.myPageNavGraph(
    onBack: () -> Unit,
    navigateToNotificationSetting: ()->Unit,
    navigateToTerms: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit
) {
    composable<Route.MyPage> {
        MyPageScreen(onBackClick = onBack,
            navigateToNotificationSetting = navigateToNotificationSetting,
            navigateToTerms = navigateToTerms,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy)
    }
}

fun NavGraphBuilder.notificationSettingNavGraph(
    onBack: () -> Unit
) {
    composable<Route.NotificationSetting> {
        NotificationSettingScreen(onBack = onBack)
    }
}

fun NavGraphBuilder.policyWebViewNavGraph(
    onClose: () -> Unit
) {
    composable(
        route = RoutePath.PolicyBase,
        arguments = listOf(
            navArgument("title") { type = NavType.StringType },
            navArgument("url") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val title = backStackEntry.arguments?.getString("title") ?: ""
        val url = backStackEntry.arguments?.getString("url") ?: ""

        PolicyWebViewScreen(
            title = title,
            url = url,
            onClose = onClose
        )
    }
}
