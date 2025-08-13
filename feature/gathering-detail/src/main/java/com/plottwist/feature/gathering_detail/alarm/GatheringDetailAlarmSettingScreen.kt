package com.plottwist.feature.gathering_detail.alarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.domain.model.gatheringIntervalDaysOptions
import com.plottwist.core.ui.component.RadioButtonItem
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.component.TukScaffold
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun GatheringDetailAlarmSettingScreen(
    onNavigateBack: () -> Unit,
    onNavigateGatheringDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GatheringDetailAlarmSettingViewModel = hiltViewModel()
) {

    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            GatheringDetailAlarmSettingSideEffect.NavigateBack -> {
                onNavigateBack()
            }

            is GatheringDetailAlarmSettingSideEffect.NavigateGatheringDetail -> {
                onNavigateGatheringDetail(sideEffect.gatheringId)
            }
        }
    }

    GatheringDetailAlarmSettingScreen(
        modifier = modifier,
        selectedIntervalDays = state.selectedIntervalDays,
        onBackClick = {
            viewModel.handleAction(GatheringDetailAlarmSettingAction.ClickBack)
        },
        onOptionSelected = {
            viewModel.handleAction(GatheringDetailAlarmSettingAction.ClickOption(it))
        },
        onSaveClick = {
            viewModel.handleAction(GatheringDetailAlarmSettingAction.ClickSave)
        }
    )
}

@Composable
private fun GatheringDetailAlarmSettingScreen(
    selectedIntervalDays: Int,
    onBackClick : () -> Unit,
    onOptionSelected: (Int) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukScaffold(
        modifier = modifier,
        topBar = {
            TukTopAppBar(
                modifier = modifier,
                actionButtons = {
                    TopAppBarCloseButton(
                        onCloseClicked = onBackClick
                    )
                }
            )
        },
        title = "앞으로는 얼마나\n자주 만나면 좋을까요?",
        description = "부담없는 주기가 제일 오래가요",
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(
                        vertical = 17.dp,
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukSolidButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "저장하기",
                    buttonType = TukSolidButtonType.from(selectedIntervalDays != 0),
                    onClick = onSaveClick
                )
            }
        }
    ) {

        itemsIndexed(gatheringIntervalDaysOptions) { index, (days, title, subtitle) ->
            RadioButtonItem(
                title = title,
                subtitle = subtitle,
                selected = selectedIntervalDays == days,
                onClick = { onOptionSelected(days) },
                hasDivider = index < gatheringIntervalDaysOptions.lastIndex
            )
        }
    }
}
