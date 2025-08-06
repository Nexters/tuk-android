package com.example.create_gathering.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringSelectIntervalDays(
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
    onNext: () -> Unit
) {
    val options = listOf(
        Triple(30, "한달", "매월 만남"),
        Triple(60, "2개월", "2개월 마다 만남"),
        Triple(90, "3개월", "분기별 만남"),
        Triple(180, "6개월", "6개월 마다 만남")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 17.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        StableImage(drawableResId = R.drawable.image_interval_days_title)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "부담없는 주기가 제일 오래가요",
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
            modifier = Modifier.padding(bottom = 48.dp)
        )

        options.forEachIndexed { index, (days, title, subtitle) ->
            RadioButtonItem(
                title = title,
                subtitle = subtitle,
                selected = selectedOption == days,
                onClick = { onOptionSelected(days) },
                hasDivider = index < options.lastIndex
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TukOutlinedButton(
                modifier = Modifier.weight(1f),
                text = "이전",
                onClick = {
                    // TODO 이전 화면
                }
            )

            TukSolidButton(
                modifier = Modifier.weight(1f),
                text = "다음",
                buttonType = TukSolidButtonType.from(selectedOption != 0),
                onClick = onNext
            )
        }
        Text(
            text = "건너뛰기",
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = { }),
            style = TextStyle(
                fontSize = 12.sp,
                color = Color(0xFF9E9E9E),
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewIntervalDays() {
    CreateGatheringSelectIntervalDays(0,{}) { }
}
