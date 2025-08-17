package com.plottwist.feature.webview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.webview.WebViewScreen

fun NavController.navigateToWebView(
    encodedUrl: String,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = Route.WebView(encodedUrl),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.webViewNavGraph(
    onBack: () -> Unit,
) {
    composable<Route.WebView> {
        WebViewScreen(
            onBack = onBack
        )
    }
}
