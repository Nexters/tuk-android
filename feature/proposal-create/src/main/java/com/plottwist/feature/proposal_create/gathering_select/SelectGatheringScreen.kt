package com.plottwist.feature.proposal_create.gathering_select

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.ui.component.RadioButtonItem
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.component.TukScaffold
import com.plottwist.feature.proposal_create.model.GatheringUiModel
import com.plottwist.feature.proposal_create.model.SelectedGatheringParam
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SelectGatheringScreen(
    onBack: () -> Unit,
    backToCreateProposal: (SelectedGatheringParam) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SelectGatheringViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            SelectGatheringSideEffect.NavigateBack -> {
                onBack()
            }
            is SelectGatheringSideEffect.NavigateToCreateProposal -> {
                backToCreateProposal(sideEffect.selectedGathering)
            }
        }
    }

    SelectGatheringScreen(
        modifier = modifier,
        gatherings = state.gatherings,
        onBackClick = { viewModel.handleAction(SelectGatheringAction.ClickBack) },
        onGatheringClick = {
            viewModel.handleAction(SelectGatheringAction.SelectGathering(it))
        },
        onProposalClick = {
            viewModel.handleAction(SelectGatheringAction.ClickPropose)
        }
    )
}

@Composable
private fun SelectGatheringScreen(
    gatherings: List<GatheringUiModel>,
    onBackClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    onProposalClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukScaffold(
        modifier = modifier.fillMaxSize(),
        title = stringResource(R.string.select_gathering_title),
        description = stringResource(R.string.select_gathering_description),
        topBar = {
            SelectGatheringAppBar(onBackClick = onBackClick)
        },
        bottomBar = {
            TukSolidButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                text = stringResource(R.string.create_proposal_propose),
                buttonType = TukSolidButtonType.from(gatherings.any { it.selected }),
                onClick = onProposalClick
            )
        }
    ) {
        itemsIndexed(gatherings) { index , gathering ->
            RadioButtonItem(
                title = gathering.name,
                subtitle = "${stringResource(R.string.gathering_detail_last_alarm_title)} ${gathering.lastNotificationRelativeTime}",
                selected = gathering.selected,
                onClick = {
                    onGatheringClick(gathering.id)
                },
                hasDivider = index != gatherings.lastIndex
            )
        }
    }
}

@Composable
fun SelectGatheringAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = stringResource(R.string.select_gathering_topbar_title),
        onBack = onBackClick,
    )
}

@Preview
@Composable
private fun SelectGatheringScreenPreview() {
    SelectGatheringScreen(
        onBackClick = {},
        onGatheringClick = {},
        onProposalClick = {},
        gatherings = emptyList(),
    )
}
