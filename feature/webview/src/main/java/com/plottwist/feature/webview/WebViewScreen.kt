package com.plottwist.feature.webview

import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.activity.compose.BackHandler
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
import com.plottwist.core.ui.web.component.TukWebView
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

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            WebViewSideEffect.NavigateBack -> {
                onBack()
            }
            is WebViewSideEffect.UpdateSessionStorage -> {
                sideEffect.webView.evaluateJavascript(
                    "window.sessionStorage.setItem('accessToken', '${sideEffect.accessToken}')",
                    null
                )
            }
        }
    }

    BackHandler {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            onBack()
        }
    }

    WebViewScreen(
        modifier = modifier,
        onBackClick = { viewModel.handleAction(WebViewAction.ClickBack) },
        onWebViewCreated = {
            webView = it
        },
        onPageFinished = {
            viewModel.handleAction(WebViewAction.OnPageFinished(it))
        },
        onRequestTokenRefresh = {
            webView?.reload()
        },
        url = state.url
    )
}

@Composable
private fun WebViewScreen(
    onBackClick: () -> Unit,
    url: String,
    onWebViewCreated : (WebView) -> Unit,
    onPageFinished: (webView: WebView) -> Unit,
    onRequestTokenRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukWebView(
        modifier = modifier.statusBarsPadding(),
        url = url,
        onWebViewCreated = onWebViewCreated,
        onPageFinished = onPageFinished,
        addBridge = {
            it.addJavascriptInterface(
                DefaultBridge(
                    onNavigateBack = onBackClick,
                    onRequestTokenRefresh = onRequestTokenRefresh
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

private class DefaultBridge(
    val onNavigateBack: () -> Unit,
    val onRequestTokenRefresh: () -> Unit
) {
    @JavascriptInterface
    fun navigateBack() {
        onNavigateBack()
    }

    @JavascriptInterface
    fun requestTokenRefresh() {
        onRequestTokenRefresh()
    }
}

@Preview
@Composable
private fun WebViewScreenPreview() {
    WebViewScreen(
        onBackClick = {},
        url = "https://www.google.com",
        onWebViewCreated = {},
        onPageFinished = {},
        onRequestTokenRefresh = {}
    )
}
