package com.plottwist.feature.webview

import android.webkit.WebView

data class WebViewState(
    val url: String = ""
)

sealed class WebViewAction {
    data object ClickBack : WebViewAction()
    data class OnPageFinished(val webView: WebView) : WebViewAction()
    data class OnRequestTokenRefresh(val webView: WebView) : WebViewAction()
}

sealed class WebViewSideEffect {
    data object NavigateBack : WebViewSideEffect()
    data class UpdateSessionStorage(
        val webView: WebView,
        val accessToken: String
    ) : WebViewSideEffect()
}
