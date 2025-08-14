package com.plottwist.create_gathering.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.component.TukOutlinedButton
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.domain.model.gatheringIntervalDaysOptions
import com.plottwist.core.ui.component.RadioButtonItem
import com.plottwist.core.ui.component.TukScaffold

@Composable
fun CreateGatheringSelectIntervalDays(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    onClickPrev: () -> Unit,
    onNext: () -> Unit
) {
    TukScaffold(
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
                TukOutlinedButton(
                    modifier = Modifier.weight(1f),
                    text = "이전",
                    onClick = onClickPrev
                )

                TukSolidButton(
                    modifier = Modifier.weight(1f),
                    text = "다음",
                    buttonType = TukSolidButtonType.from(selectedOption != 0),
                    onClick = onNext
                )
            }
        }
    ) {

        itemsIndexed(gatheringIntervalDaysOptions) { index, (days, title, subtitle) ->
            RadioButtonItem(
                title = title,
                subtitle = subtitle,
                selected = selectedOption == days,
                onClick = { onOptionSelected(days) },
                hasDivider = index < gatheringIntervalDaysOptions.lastIndex
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewIntervalDays() {
    CreateGatheringSelectIntervalDays(0,{}, {}) { }
}
