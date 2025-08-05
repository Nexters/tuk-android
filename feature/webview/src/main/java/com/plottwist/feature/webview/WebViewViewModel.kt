package com.plottwist.feature.webview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ContainerHost<WebViewState, WebViewSideEffect>, ViewModel() {
    override val container = container<WebViewState, WebViewSideEffect>(WebViewState(
        url = URLDecoder.decode(savedStateHandle.toRoute<Route.WebView>().url, "UTF-8")
    ))

    fun handleAction(action: WebViewAction) {
        when (action) {
            WebViewAction.ClickBack -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(WebViewSideEffect.NavigateBack)
    }
}
