package com.plottwist.feature.proposal_create.gathering_proposal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.ui.component.TopAppBarCloseButton
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateGatheringProposalScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateGatheringProposalViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            CreateGatheringProposalSideEffect.NavigateBack -> onBack()
        }
    }

    CreateGatheringProposalScreen(
        modifier = modifier,
        onBackClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickBack) }
    )
}

@Composable
private fun CreateGatheringProposalScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CreateGatheringProposalAppBar(onCloseClicked = onBackClick)
    }
}

@Composable
fun CreateGatheringProposalAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarCloseButton(
                onCloseClicked = onCloseClicked
            )
        }
    )
}

@Preview
@Composable
private fun CreateGatheringProposalScreenPreview() {
    CreateGatheringProposalScreen(
        onBackClick = {}
    )
}
