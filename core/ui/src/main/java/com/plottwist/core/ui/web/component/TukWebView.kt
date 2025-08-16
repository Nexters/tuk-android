package com.plottwist.core.ui.web.component

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.plottwist.core.ui.web.client.TukWebViewClient

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TukWebView(
    url: String,
    onWebViewCreated: (webView: WebView) -> Unit,
    onPageFinished: (webView: WebView) -> Unit,
    modifier: Modifier = Modifier,
    addBridge: (webView: WebView)-> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webViewClient = TukWebViewClient(
                    onPageFinished = onPageFinished
                )

                settings.run {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    databaseEnabled = true

                    loadWithOverviewMode = true
                    useWideViewPort = true
                    setSupportZoom(true)
                    setInitialScale(1)
                }

                addBridge(this)

                onWebViewCreated(this)
            }
        },
        update = { webView ->
            onWebViewCreated(webView)
            webView.loadUrl(url)
        }
    )
}
