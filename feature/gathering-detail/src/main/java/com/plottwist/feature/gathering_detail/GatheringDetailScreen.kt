package com.plottwist.feature.gathering_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography

@Composable
fun GatheringDetailScreen(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun GatheringDetailScreen(
    gatheringTitle: String,
    onAlarmSettingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        item (key = Items.TITLE) {
            GatheringTitle(gatheringTitle)
        }

        item (key = Items.ALARM_SETTING) {
            GatheringAlarmSetting(
                modifier = Modifier.padding(top = 24.dp),
                onClick = onAlarmSettingClick
            )
        }

        item (key = Items.GATHERING_INFO) {

        }

        item (key = Items.MEMBERS) {

        }
    }
}

@Composable
fun GatheringTitle(
    gatheringTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.width(185.dp),
        text = gatheringTitle,
        style = TukSerifTypography.title24M.copy(
            lineBreak = LineBreak.Heading
        )
    )
}

@Composable
fun GatheringAlarmSetting(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_setting),
            contentDescription = "setting",
            tint = Color(0xFF888888)
        )

        Text(
            text = stringResource(R.string.gathering_detail_alarm_setting),
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888)
        )
    }
}

private enum class Items {
    TITLE,
    ALARM_SETTING,
    GATHERING_INFO,
    MEMBERS
}

@Preview
@Composable
private fun GatheringDetailScreenPreview() {
    GatheringDetailScreen(
        modifier = Modifier.fillMaxSize(),
        gatheringTitle = "다음 만남은 계획대로 되지 않아",
        onAlarmSettingClick = {}
    )
}
