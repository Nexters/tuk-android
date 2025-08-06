package com.plottwist.feature.proposal_create.gathering_select

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.ui.component.RadioButtonItem
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.component.TukScaffold
import com.plottwist.feature.proposal_create.model.GatheringUiModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SelectGatheringScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SelectGatheringViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            SelectGatheringSideEffect.NavigateBack -> onBack()
            is SelectGatheringSideEffect.NavigateToCreateProposal -> Unit
        }
    }

    SelectGatheringScreen(
        modifier = modifier,
        gatherings = state.gatherings,
        onBackClick = { viewModel.handleAction(SelectGatheringAction.ClickBack) },
        onGatheringClick = {
            viewModel.handleAction(SelectGatheringAction.SelectGathering(it))
        }
    )
}

@Composable
private fun SelectGatheringScreen(
    gatherings: List<GatheringUiModel>,
    onBackClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    TukScaffold(
        modifier = modifier.fillMaxSize(),
        title = stringResource(R.string.select_gathering_title),
        description = stringResource(R.string.select_gathering_description),
        topBar = {
            SelectGatheringAppBar(onBackClick = onBackClick)
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
        type = TukTopAppBarType.PLAIN,
        actionButtons = {
            TopAppBarCloseButton(onCloseClicked = onBackClick)
        }
    )
}

@Preview
@Composable
private fun SelectGatheringScreenPreview() {
    SelectGatheringScreen(
        onBackClick = {},
        onGatheringClick = {},
        gatherings = emptyList(),
    )
}
