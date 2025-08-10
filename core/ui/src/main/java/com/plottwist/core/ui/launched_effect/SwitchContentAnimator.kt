package com.plottwist.core.ui.launched_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun <T> SwitchContentAnimator(
    items: List<T>,
    isStopped: Boolean,
    durationShowItem: Long,
    durationAnimation: Int,
    content: @Composable (
        index: Int
    ) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var currentIndex by remember { mutableIntStateOf(0) }
    var animated by remember { mutableStateOf(false) }

    LaunchedEffect(lifecycleOwner, isStopped) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (isActive && items.isNotEmpty() && !isStopped) {
                animated = false
                delay(durationShowItem)

                animated = true
                delay(durationAnimation.toLong())
                currentIndex = (currentIndex + 1) % items.size
            }
        }
    }
    content(currentIndex)
}
