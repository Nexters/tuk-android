package com.plottwist.feature.proposal_create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.ui.component.TopAppBarCloseButton

@Composable
fun CreateProposalScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateProposalViewModel = hiltViewModel()
) {
    CreateProposalScreen(
        modifier = modifier,
        onCloseClicked = {
            viewModel.handleAction(CreateProposalAction.ClickClose)
        }
    )
}

@Composable
private fun CreateProposalScreen(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CreateProposalAppBar(onCloseClicked = onCloseClicked)
    }
}

@Composable
fun CreateProposalAppBar(
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
private fun CreateProposalScreenPreview() {
    CreateProposalScreen(
        onCloseClicked = {}
    )
}
