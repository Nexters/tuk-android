package com.plottwist.feature.webview.client

import android.webkit.WebView
import android.webkit.WebViewClient

class TukWebViewClient(
    val onPageFinished: (view: WebView) -> Unit,
): WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.let { onPageFinished(it) }
    }
}
