package com.plottwist.feature.main

data class MainState(
    val isReady: Boolean = false,
)

sealed class MainAction {

}

sealed class MainSideEffect {

}
