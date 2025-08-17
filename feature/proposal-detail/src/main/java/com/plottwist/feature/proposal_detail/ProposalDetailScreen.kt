package com.plottwist.feature.proposal_detail

import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.web.component.BRIDGE_NAME
import com.plottwist.core.ui.web.component.DefaultBridge
import com.plottwist.core.ui.web.component.TukWebView
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ProposalDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProposalDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    var webView by remember { mutableStateOf<WebView?>(null) }

    viewModel.collectSideEffect {
        when (it) {
            ProposalDetailSideEffect.NavigateBack -> {
                onBack()
            }
            ProposalDetailSideEffect.ProposalAccepted -> { /* Handle accepted */ }
            ProposalDetailSideEffect.ProposalRejected -> { /* Handle rejected */ }
            is ProposalDetailSideEffect.ShowToast -> { /* Show toast */ }
        }
    }

    ProposalDetailScreen(
        modifier = modifier,
        proposalId = state.proposalId,
        onBackClick = { viewModel.handleAction(ProposalDetailAction.ClickBack) },
        onPageFinished = {

        },
        onWebViewCreated = {
            webView = it
        }
    )
}

@Composable
private fun ProposalDetailScreen(
    proposalId: Long?,
    onBackClick: () -> Unit,
    onWebViewCreated : (WebView) -> Unit,
    onPageFinished: (webView: WebView) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().statusBarsPadding()
    ) {
        TukWebView(
            url = "https://www.tuk.kr/proposal/${proposalId}/detail",
            onWebViewCreated = onWebViewCreated,
            onPageFinished = onPageFinished,
            addBridge = {
                it.addJavascriptInterface(
                    DefaultBridge(
                        onNavigateBack = onBackClick,
                        onRequestTokenRefresh = {

                        }
                    ),
                    BRIDGE_NAME
                )
            }
        )
    }
}

@Composable
fun ProposalDetailAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarCloseButton(
                onCloseClicked = onBackClick
            )
        }
    )
}

@Preview
@Composable
private fun ProposalDetailScreenPreview() {
    ProposalDetailScreen(
        proposalId = 123L,
        onBackClick = {},
        onWebViewCreated = {},
        onPageFinished = {},
    )
}
