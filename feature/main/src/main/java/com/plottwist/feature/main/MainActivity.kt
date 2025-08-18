package com.plottwist.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.core.net.toUri
import com.plottwist.feature.main.ui.theme.TukTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleDeepLink(intent)
        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            CompositionLocalProvider(
                LocalActivity provides this
            ) {
                TukTheme {
                    TukApp()
                }
            }

        }
    }

    private fun handleDeepLink(
        intent: Intent,
    ) {
        val deepLink = intent.extras?.getString("deepLink") ?: return

        startActivity(
            Intent(Intent.ACTION_VIEW).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                data = deepLink.toUri()
            }
        )
    }


    companion object {
        const val NOTIFICATION_REQUEST_CODE = 0
    }
}

val LocalActivity = compositionLocalOf<ComponentActivity> {
    error("CompositionLocal LocalActivity not present")
}
