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
import com.example.create_gathering.CreateGatheringAction
import com.example.create_gathering.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage

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
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            style = TukPretendardTypography.body14M
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = subtitle,
                            style = TukPretendardTypography.body12L,
                            color = Color(0xFF999999)
                        )
                    }

                    RadioButton(
                        selected = selectedOption == days,
                        onClick = { onOptionSelected(days) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Black,
                            unselectedColor = Color(0xFFCCCCCC)
                        )
                    )
                }

                if (index < options.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(0.dp),
                        thickness = 1.dp,
                        color = Color(0xFFEEEEEE)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(53.dp),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "이전",
                    style = TukPretendardTypography.body16M
                )
            }

            Button(
                onClick = { onNext()},
                modifier = Modifier
                    .weight(1f)
                    .height(53.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFCCCCCC),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "다음",
                    style = TukPretendardTypography.body16M
                )
            }

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
