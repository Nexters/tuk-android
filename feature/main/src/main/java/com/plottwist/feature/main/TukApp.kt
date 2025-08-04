package com.plottwist.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.plottwist.feature.main.ui.component.TukNavHost

@Composable
fun TukApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        TukNavHost(
            modifier = Modifier.padding(
                bottom = innerPadding.calculateBottomPadding()
            )
        )
    }
}
