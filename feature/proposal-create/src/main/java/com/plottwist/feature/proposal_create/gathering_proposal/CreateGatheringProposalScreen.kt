package com.plottwist.feature.proposal_create.gathering_proposal

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.feature.proposal_create.CreateProposalGradientBackgroundImage
import com.plottwist.feature.proposal_create.component.CreateGatheringProposalPostCard
import com.plottwist.feature.proposal_create.component.CreateProposalPostCard
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateGatheringProposalScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateGatheringProposalViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            CreateGatheringProposalSideEffect.NavigateBack -> {
                onBack()
            }
        }
    }

    CreateGatheringProposalScreen(
        modifier = modifier,
        whenLabel = state.whenLabel,
        whereLabel = state.whereLabel,
        whatLabel = state.whatLabel,
        isReady = state.isReady,
        selectedGatheringName = state.gatheringName,
        onBackClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickBack) },
        onWhenRefreshClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickWhenRefresh) },
        onWhereRefreshClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickWhereRefresh) },
        onWhatRefreshClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickWhatRefresh) },
        onNextClick = { viewModel.handleAction(CreateGatheringProposalAction.ClickNext) }
    )
}

@Composable
private fun CreateGatheringProposalScreen(
    whenLabel: TextFieldState,
    whereLabel: TextFieldState,
    whatLabel: TextFieldState,
    isReady: Boolean,
    selectedGatheringName: String,
    onBackClick: () -> Unit,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    verticalState: ScrollState = rememberScrollState(),
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
    ) {
        CreateProposalGradientBackgroundImage(
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CreateGatheringProposalAppBar(onCloseClicked = onBackClick)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(verticalState)
                    .imePadding()
                    .weight(1f),
            ) {
                Text(
                    modifier = modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 66.dp),
                    text = stringResource(R.string.create_proposal_title),
                    style = TukSerifTypography.title24M,
                    color = Gray900
                )

                if(isReady) {
                    CreateProposalPostCard(
                        modifier = Modifier
                            .width(300.dp)
                            .height(370.dp)
                            .align(Alignment.CenterHorizontally),
                        whereLabel = whereLabel.text.toString(),
                        whenLabel = whenLabel.text.toString(),
                        whatLabel = whatLabel.text.toString(),
                        selectedGatheringName = selectedGatheringName,
                        isEnabledEditGatheringName = false,
                        onSelectGatheringClick = { },
                        onCloseSelectedGatheringClick = {}
                    )
                } else {
                    CreateGatheringProposalPostCard(
                        modifier = Modifier
                            .width(300.dp)
                            .height(370.dp)
                            .align(Alignment.CenterHorizontally)
                        ,
                        whereLabel = whereLabel,
                        whenLabel = whenLabel,
                        whatLabel = whatLabel,
                        onWhenRefreshClick = onWhenRefreshClick,
                        onWhereRefreshClick = onWhereRefreshClick,
                        onWhatRefreshClick = onWhatRefreshClick
                    )
                }

            }

            TukSolidButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                text = if(isReady) "제안하기" else stringResource(R.string.common_next),
                buttonType = TukSolidButtonType.ACTIVE,
                onClick = onNextClick
            )
        }
    }
}

@Composable
fun CreateGatheringProposalAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarCloseButton(
                onCloseClicked = onCloseClicked
            )
        }
    )
}

@Preview
@Composable
private fun CreateGatheringProposalScreenPreview() {
    CreateGatheringProposalScreen(
        onBackClick = {},
        whenLabel = TextFieldState(),
        whereLabel = TextFieldState(),
        whatLabel = TextFieldState(),
        onWhenRefreshClick = {},
        onWhereRefreshClick = {},
        onWhatRefreshClick = {},
        isReady = false,
        selectedGatheringName = "",
        onNextClick = {}
    )
}
