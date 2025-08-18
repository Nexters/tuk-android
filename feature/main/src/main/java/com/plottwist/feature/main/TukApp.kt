package com.plottwist.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.plottwist.feature.main.ui.component.TukNavHost

@Composable
fun TukApp(
    modifier: Modifier = Modifier
) {
    Surface (
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        color = Color.White
    ) {
        TukNavHost(
            modifier = Modifier.fillMaxSize()
        )
    }
}
