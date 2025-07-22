package com.plottwist.core.ui.navigation

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object Home: Route
}
