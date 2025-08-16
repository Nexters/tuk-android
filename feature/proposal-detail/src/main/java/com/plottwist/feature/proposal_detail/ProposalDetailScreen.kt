package com.plottwist.feature.proposal_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun ProposalDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProposalDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

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
        onAcceptClick = { viewModel.handleAction(ProposalDetailAction.ClickAccept) },
        onRejectClick = { viewModel.handleAction(ProposalDetailAction.ClickReject) }
    )
}

@Composable
private fun ProposalDetailScreen(
    proposalId: Long?,
    onBackClick: () -> Unit,
    onAcceptClick: () -> Unit,
    onRejectClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProposalDetailAppBar(onBackClick = onBackClick)
        // TODO: Add content for proposal detail
    }
}

@Composable
fun ProposalDetailAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = "제안 상세",
        onBack = onBackClick
    )
}

@Preview
@Composable
private fun ProposalDetailScreenPreview() {
    ProposalDetailScreen(
        proposalId = 123L,
        onBackClick = {},
        onAcceptClick = {},
        onRejectClick = {}
    )
}
