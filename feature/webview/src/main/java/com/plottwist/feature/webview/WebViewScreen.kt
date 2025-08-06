package com.plottwist.feature.webview

import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.feature.webview.component.TukWebView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WebViewScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WebViewViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    var webView : WebView? by remember { mutableStateOf(null) }

    viewModel.collectSideEffect {
        when (it) {
            WebViewSideEffect.NavigateBack -> onBack()
        }
    }

    WebViewScreen(
        modifier = modifier,
        onBackClick = { viewModel.handleAction(WebViewAction.ClickBack) },
        onWebViewCreated = {
            webView = it
        },
        url = state.url
    )
}

@Composable
private fun WebViewScreen(
    onBackClick: () -> Unit,
    url: String,
    onWebViewCreated : (WebView) -> Unit,
    modifier: Modifier = Modifier,
) {
    TukWebView(
        modifier = modifier.statusBarsPadding(),
        url = url,
        onWebViewCreated = onWebViewCreated,
        addBridge = {
            it.addJavascriptInterface(
                DefaultBridge(
                    onNavigateHome = onBackClick
                ),
                BRIDGE_NAME
            )
        }
    )
}

@Composable
fun WebViewAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        onBack = onBackClick
    )
}

internal const val BRIDGE_NAME = "AndroidBridge"

private class DefaultBridge(val onNavigateHome: () -> Unit) {
    @JavascriptInterface
    fun navigateHome() {
        onNavigateHome()
    }
}

@Preview
@Composable
private fun WebViewScreenPreview() {
    WebViewScreen(
        onBackClick = {},
        url = "https://www.google.com",
        onWebViewCreated = {},
    )
}
