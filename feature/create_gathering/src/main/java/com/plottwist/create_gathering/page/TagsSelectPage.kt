package com.plottwist.create_gathering.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
 import com.plottwist.create_gathering.model.GatheringTag
import com.plottwist.create_gathering.model.TagCategory
import com.plottwist.core.designsystem.component.TukOutlinedButton
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray300
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TukScaffold
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
    TukScaffold(
        title = "마지막으로\n모임에 대해 알려주세요",
        description = "만남을 가지면 주로 무엇을 하나요" + " ${selectedTags.size}/5",
        bottomBar = {
            Column (
                modifier = Modifier.padding(
                    vertical = 17.dp,
                    horizontal = 20.dp
                )
            ) {
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
            }
        }
    ) {
        items(categories) { category ->
            CategoryItem(
                title = category.categoryName,
                tags = category.tags,
                selectedTags = selectedTags,
                onToggle = onToggle
            )
            Spacer(modifier = Modifier.height(24.dp))
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
                color = if (isSelected) Gray900 else Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Gray900  else Gray300,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
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
