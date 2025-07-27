package com.example.create_gathering.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.OutlinedButton
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
import com.example.create_gathering.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage

@Composable
fun CreateGatheringSelectChips(
    selectedTags: List<String>,
    onToggle: (String) -> Unit,
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    onClickSkip: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        StableImage(drawableResId = R.drawable.image_chip_select_title)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "만남을 가지면 주로 무엇을 하나요",
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
        )

        Spacer(modifier = Modifier.height(24.dp))

        val activityTags = listOf("밥 먹기", "드라이브", "호캉스", "당일치기 여행", "보드게임", "연애 이야기")
        val homeTags = listOf("디저트 투어", "영화 보기", "집콕 수다", "요리하기")
        val travelTags = listOf("국내여행", "해외여행", "캠핑", "산책")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            CategoryItem("액티비티", activityTags, selectedTags, onToggle)
            Spacer(modifier = Modifier.height(24.dp))
            CategoryItem("집에서", homeTags, selectedTags, onToggle)
            Spacer(modifier = Modifier.height(24.dp))
            CategoryItem("여행/나들이", travelTags, selectedTags, onToggle)

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onClickPrev,
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
                    Text("이전", style = TukPretendardTypography.body16M)
                }

                Button(
                    onClick = onClickNext,
                    modifier = Modifier
                        .weight(1f)
                        .height(53.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4B0404),
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("생성하기", style = TukPretendardTypography.body16M)
                }
            }

            Text(
                text = "건너뛰기",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = onClickSkip),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color(0xFF9E9E9E),
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Composable
fun CategoryItem(
    title: String,
    tags: List<String>,
    selectedTags: List<String>,
    onToggle: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            color = Color(0xFF9E9E9E),
            style = TukPretendardTypography.body12R
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tags.forEach { tag ->
                SelectableTag(tag, selectedTags, onToggle)
            }
        }
    }
}

@Composable
fun SelectableTag(
    tag: String,
    selectedTags: List<String>,
    onToggle: (String) -> Unit
) {
    val isSelected = selectedTags.contains(tag)

    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF4B0000) else Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF4B0000) else Color(0xFFE0E0E0),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onToggle(tag) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = tag,
            style = TukPretendardTypography.body14R,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewTagOption() {
    CreateGatheringSelectChips(emptyList(), {}, {}, {}, {})
}
