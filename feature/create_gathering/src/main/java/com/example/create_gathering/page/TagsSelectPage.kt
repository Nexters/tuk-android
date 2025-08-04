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
 import com.example.create_gathering.model.GatheringTag
import com.example.create_gathering.model.TagCategory
import com.plottwist.core.designsystem.component.TukOutlinedButton
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringSelectTags(
    categories: List<TagCategory>,
    selectedTags: List<GatheringTag>,
    onToggle: (GatheringTag) -> Unit,
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            categories.forEach { category ->
                CategoryItem(
                    title = category.categoryName,
                    tags = category.tags,
                    selectedTags = selectedTags,
                    onToggle = onToggle
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TukOutlinedButton(
                    modifier = Modifier.weight(1f),
                    text = "이전",
                    onClick = onClickPrev
                )

                TukSolidButton(
                    modifier = Modifier.weight(1f),
                    text = "생성하기",
                    buttonType = TukSolidButtonType.from(selectedTags.isNotEmpty()),
                    onClick = onClickNext
                )
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
    tags: List<GatheringTag>,
    selectedTags: List<GatheringTag>,
    onToggle: (GatheringTag) -> Unit
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
    tag: GatheringTag,
    selectedTags: List<GatheringTag>,
    onToggle: (GatheringTag) -> Unit
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
            text = tag.name, // 🔥 수정: text = tag → text = tag.name
            style = TukPretendardTypography.body14R,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTagOption() {
    CreateGatheringSelectTags(emptyList(), emptyList(),{}, {}, {}, {})
}
