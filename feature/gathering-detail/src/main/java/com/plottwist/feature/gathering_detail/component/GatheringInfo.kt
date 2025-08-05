package com.plottwist.feature.gathering_detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukRoundSolidButton
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography

@Composable
fun GatheringInfo(
    lastAlarm: String,
    sentProposalCount: Int,
    receivedProposalCount: Int,
    onProposalClick: () -> Unit,
    onSentProposalClick: () -> Unit,
    onReceivedProposalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        GatheringInfoHeader(
            lastAlarm = lastAlarm,
            onProposalClick = onProposalClick
        )

        HorizontalDivider(color = Color(0xFFEFEFEF))

        ProposalItem(
            label = stringResource(R.string.gathering_detail_sent_invitation),
            count = sentProposalCount,
            onProposalClick = onSentProposalClick
        )

        ProposalItem(
            label = stringResource(R.string.gathering_detail_received_invitation),
            count = receivedProposalCount,
            onProposalClick = onReceivedProposalClick
        )

    }
}

@Composable
fun GatheringInfoHeader(
    lastAlarm : String,
    onProposalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        LastAlarmInfo(lastAlarm)

        TukRoundSolidButton(
            modifier = Modifier,
            text = stringResource(R.string.home_bottom_sheet_nudging_text),
            containerColor = Color(0xFFFF3838),
            contentColor = Color(0xFFFFFFFF),
            onClick = onProposalClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle),
                contentDescription = stringResource(R.string.home_bottom_create_gathering_button_text),
                tint = Color(0xFFFFFFFF)
            )
        }
    }
}



@Composable
fun LastAlarmInfo(
    lastAlarm: String,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.gathering_detail_last_alarm_title),
            style = TukSerifTypography.body14R,
            color = Color(0xFF888888)
        )

        Text(
            text = lastAlarm,
            style = TukSerifTypography.title22M,
            color = Color(0xFF1F1F1F)
        )
    }
}

@Composable
fun ProposalItem(
    label: String,
    count: Int,
    onProposalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = null,
            indication = null
        ) {
            onProposalClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            style = TukPretendardTypography.body14M,
            color = Color(0xFF1F1F1F)
        )
        Text(
            text = "$count",
            style = TukPretendardTypography.body14M,
            color = Color(0xFF1F1F1F)
        )
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_next_arrow),
            contentDescription = "invitation_item_${label}",
            tint =  Color(0xFF1F1F1F)
        )
    }
}
