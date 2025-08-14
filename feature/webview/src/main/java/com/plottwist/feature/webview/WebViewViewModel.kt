package com.plottwist.feature.webview

import android.webkit.WebView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.auth.usecase.GetAccessTokenUseCase
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
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

            is WebViewAction.OnPageFinished -> {
                handlePageFinished(action.webView)
            }

            is WebViewAction.OnRequestTokenRefresh -> {
                // TODO 토큰 재발급 후 액세스 토큰 웹뷰에 보내기 로직
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(WebViewSideEffect.NavigateBack)
    }

    private fun handlePageFinished(webView: WebView) = intent {
        getAccessTokenUseCase().collectLatest { accessToken ->
            accessToken?.let {
                postSideEffect(WebViewSideEffect.UpdateSessionStorage(webView, it))
            }
        }
    }
}
