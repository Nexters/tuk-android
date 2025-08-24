package com.plottwist.feature.gathering_detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.domain.model.GatheringMember
import com.plottwist.feature.gathering_detail.component.GatheringInfo
import com.plottwist.feature.gathering_detail.component.GatheringMembers
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun GatheringDetailScreen(
    onBack: () -> Unit,
    navigateToWebViewScreen: (String) -> Unit,
    navigateToInviteGathering: (String) -> Unit,
    navigateToCreateGatheringProposal: (Long, String) -> Unit,
    navigateToGatheringDetailAlarmSetting: (Long, Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GatheringDetailViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            GatheringDetailSideEffect.NavigateBack -> {
                onBack()
            }

            is GatheringDetailSideEffect.NavigateToWebView -> {
                navigateToWebViewScreen(sideEffect.encodedUrl)
            }

            is GatheringDetailSideEffect.NavigateInviteGatheringScreen -> {
                navigateToInviteGathering(sideEffect.encodedUrl)
            }

            is GatheringDetailSideEffect.NavigateToCreateGatheringProposal -> {
                navigateToCreateGatheringProposal(sideEffect.gatheringId, sideEffect.gatheringName)
            }

            is GatheringDetailSideEffect.NavigateToGatheringDetailAlarmSetting -> {
                navigateToGatheringDetailAlarmSetting(
                    sideEffect.gatheringId,
                    sideEffect.selectedIntervalDays
                )
            }
        }
    }

    GatheringDetailScreen(
        modifier = modifier,
        members = state.gatheringDetail.members,
        gatheringTitle = state.gatheringDetail.gatheringName,
        lastAlarm = state.gatheringDetail.lastPushRelativeTime,
        sentProposalCount = state.gatheringDetail.sentProposalCount,
        receivedProposalCount = state.gatheringDetail.receivedProposalCount,
        isHost = state.gatheringDetail.isHost,
        onAlarmSettingClick = {
            viewModel.handleAction(GatheringDetailAction.ClickAlarmSetting)
        },
        onProposalClick = {
            viewModel.handleAction(GatheringDetailAction.ClickProposal)
        },
        onSentProposalClick = {
            viewModel.handleAction(GatheringDetailAction.ClickSentProposal)
        },
        onReceivedProposalClick = {
            viewModel.handleAction(GatheringDetailAction.ClickReceivedProposal)
        },
        onInviteMemberClick = {
            viewModel.handleAction(GatheringDetailAction.ClickInviteMember)
        },
        onBackClick = {
            viewModel.handleAction(GatheringDetailAction.ClickBack)
        }
    )
}

@Composable
private fun GatheringDetailScreen(
    members: List<GatheringMember>,
    gatheringTitle: String,
    lastAlarm: String,
    sentProposalCount: Int,
    receivedProposalCount: Int,
    isHost: Boolean,
    onAlarmSettingClick: () -> Unit,
    onProposalClick: () -> Unit,
    onSentProposalClick: () -> Unit,
    onReceivedProposalClick: () -> Unit,
    onInviteMemberClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFFAFAFA)
            )
    ) {
        GatheringDetailAppBar(
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            item(key = Items.TITLE) {
                GatheringTitle(gatheringTitle)
            }

            item(key = Items.ALARM_SETTING) {
                if(isHost) {
                    GatheringAlarmSetting(
                        modifier = Modifier.padding(top = 24.dp),
                        onClick = onAlarmSettingClick
                    )
                } else {
                    Spacer(modifier = Modifier.fillMaxWidth().height(44.dp))
                }
            }

            item(key = Items.GATHERING_INFO) {
                GatheringInfo(
                    modifier = Modifier
                        .padding(top = 86.dp)
                        .fillMaxWidth(),
                    lastAlarm = lastAlarm,
                    sentProposalCount = sentProposalCount,
                    receivedProposalCount = receivedProposalCount,
                    onProposalClick = onProposalClick,
                    onSentProposalClick = onSentProposalClick,
                    onReceivedProposalClick = onReceivedProposalClick
                )
            }

            item(key = Items.MEMBERS) {
                GatheringMembers(
                    modifier = Modifier.padding(top = 30.dp),
                    members = members,
                    onInviteMemberClick = onInviteMemberClick
                )
            }
        }
    }
}

@Composable
fun GatheringDetailAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = stringResource(R.string.gathering_detail_title),
        onBack = onBackClick
    )
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
    Row(
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
        members = listOf(
            GatheringMember(memberName = "정석준"),
            GatheringMember(memberName = "이준우"),
            GatheringMember(memberName = "김준식")
        ),
        gatheringTitle = "다음 만남은 계획대로 되지 않아",
        lastAlarm = "3달 전",
        sentProposalCount = 99,
        receivedProposalCount = 99,
        isHost = true,
        onAlarmSettingClick = {},
        onProposalClick = {},
        onSentProposalClick = {},
        onReceivedProposalClick = {},
        onInviteMemberClick = {},
        onBackClick = {}
    )
}
