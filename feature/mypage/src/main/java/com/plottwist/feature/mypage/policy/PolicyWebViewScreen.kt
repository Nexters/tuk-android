package com.plottwist.feature.mypage.policy

import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType

@Composable
fun PolicyWebViewScreen(
    title: String,
    url: String,
    onClose: () ->Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TukTopAppBar(
            type = TukTopAppBarType.PLAIN,
            title = title,
            actionButtons = {
                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "닫기")
                }
            }
        )

        AndroidView(
            factory = {context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}