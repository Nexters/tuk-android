package com.plottwist.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.domain.model.Gatherings

@Composable
fun GatheringsCard(
    gatherings: Gatherings,
    onCreateGatheringClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .outerDropShadow(
                shape = RoundedCornerShape(20.dp),
                blur = 6.dp
            )
            .background(
                color = Color(0x49FFFFFF),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 0.5.dp,
                color = Gray000.copy(alpha = 0.4f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CreateGatheringText(
            onAddGatheringClick = onCreateGatheringClick
        )
        if(gatherings.gatheringOverviews.isNotEmpty()) {
            HorizontalDivider(
                color = Color(0xFFEAEAEA)
            )

            gatherings.gatheringOverviews.forEach { gathering ->
                GatheringItem(
                    gatheringName = gathering.gatheringName,
                    lastAlarm = gathering.lastPushRelativeTime.toString(),
                    onClick = {
                        onGatheringClick(gathering.gatheringId)
                    }
                )
            }
        }
    }
}

@Composable
fun CreateGatheringText(
    onAddGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = null,
                indication = null
            ) {
                onAddGatheringClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
            contentDescription = "add"
        )

        Text(
            text = "모임 생성",
            style = TukPretendardTypography.body14R,
            color = Gray900
        )
    }
}

@Composable
fun GatheringItem(
    gatheringName: String,
    lastAlarm: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(vertical = 12.dp)
            .clickable(
                interactionSource = null,
                indication = null
            ) {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = gatheringName,
                style = TukPretendardTypography.body14M,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Gray900
            )

            Row (
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_last_alarm),
                    style = TukPretendardTypography.body12R,
                    color = Gray800
                )

                Text(
                    text = lastAlarm,
                    style = TukPretendardTypography.body12R,
                    color = Gray800
                )
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_next_arrow),
            contentDescription = "next arrow"
        )
    }
}

@Preview
@Composable
private fun GatheringsCardPreview() {
    GatheringsCard(
        gatherings = Gatherings(),
        onCreateGatheringClick = {},
        onGatheringClick = {}
    )
}
