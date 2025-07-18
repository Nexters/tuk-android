package com.plottwist.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.plottwist.feature.main.ui.theme.TukTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setComposeContent()
    }

    private fun setComposeContent() {
        setContent {
            TukTheme {
                TukApp()
            }
        }
    }
}
