package com.plottwist.feature.webview

data class WebViewState(
    val url: String = ""
)

sealed class WebViewAction {
    data object ClickBack : WebViewAction()
    data class LoadUrl(val url: String) : WebViewAction()
}

sealed class WebViewSideEffect {
    data object NavigateBack : WebViewSideEffect()
}
