package com.plottwist.create_gathering.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.component.TukOutlinedButton
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.RadioButtonItem
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TukScaffold
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringSelectIntervalDays(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    onClickPrev: () -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        Triple(30, "한달", "매월 만남"),
        Triple(60, "2개월", "2개월 마다 만남"),
        Triple(90, "3개월", "분기별 만남"),
        Triple(180, "6개월", "6개월 마다 만남")
    )
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

        itemsIndexed(options) { index, (days, title, subtitle) ->
            RadioButtonItem(
                title = title,
                subtitle = subtitle,
                selected = selectedOption == days,
                onClick = { onOptionSelected(days) },
                hasDivider = index < options.lastIndex
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewIntervalDays() {
    CreateGatheringSelectIntervalDays(0,{}, {}) { }
}
