package com.plottwist.feature.webview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor() : ContainerHost<WebViewState, WebViewSideEffect>, ViewModel() {

    override val container = container<WebViewState, WebViewSideEffect>(WebViewState())

    fun handleAction(action: WebViewAction) {
        when (action) {
            WebViewAction.ClickBack -> {
                navigateBack()
            }
            is WebViewAction.LoadUrl -> {
                loadUrl(action.url)
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(WebViewSideEffect.NavigateBack)
    }

    private fun loadUrl(url: String) = intent {
        reduce { state.copy(url = url) }
    }
}
