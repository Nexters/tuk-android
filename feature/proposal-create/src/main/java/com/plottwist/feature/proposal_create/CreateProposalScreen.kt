package com.plottwist.feature.proposal_create

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.navigation.NavigationConstants.KEY_SELECTED_GATHERING
import com.plottwist.feature.proposal_create.component.CreateProposalPostCard
import com.plottwist.feature.proposal_create.model.SelectedGatheringParam
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CreateProposalScreen(
    backStackEntry: NavBackStackEntry,
    onBack : () -> Unit,
    navigateToSelectGatheringScreen: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateProposalViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val selectedGatheringParam = backStackEntry.savedStateHandle.get<SelectedGatheringParam>(KEY_SELECTED_GATHERING)

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            CreateProposalSideEffect.NavigateBack -> {
                onBack()
            }

            is CreateProposalSideEffect.NavigateToSelectGathering -> {
                navigateToSelectGatheringScreen(sideEffect.selectedGatheringId)
            }
        }
    }

    LaunchedEffect(selectedGatheringParam) {
        if(selectedGatheringParam != null) {
            viewModel.handleAction(
                CreateProposalAction.SelectedGathering(
                    gatheringId = selectedGatheringParam.id,
                    gatheringName = selectedGatheringParam.name
                )
            )
        }
    }

    CreateProposalScreen(
        modifier = modifier,
        whereLabel = state.whereLabel,
        whenLabel = state.whenLabel,
        whatLabel = state.whatLabel,
        selectedGatheringName = state.selectedGathering?.name ?: "",
        isGatheringSelected = state.selectedGathering != null,
        onCloseClicked = {
            viewModel.handleAction(CreateProposalAction.ClickClose)
        },
        onSelectGatheringClick = {
            viewModel.handleAction(CreateProposalAction.ClickSelectGathering)
        },
        onCloseSelectedGatheringClick = {
            viewModel.handleAction(CreateProposalAction.ClickCloseSelectedGathering)
        }
    )
}

@Composable
private fun CreateProposalScreen(
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
    selectedGatheringName: String,
    isGatheringSelected: Boolean,
    onCloseClicked: () -> Unit,
    onSelectGatheringClick: () -> Unit,
    onCloseSelectedGatheringClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().background(color = Color(0xFFFAFAFA))
    ) {
        CreateProposalGradientBackgroundImage(
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CreateProposalAppBar(onCloseClicked = onCloseClicked)

            CreateProposalTitle()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
            ) {
                CreateProposalPostCard(
                    modifier = Modifier
                        .padding(top = 66.dp)
                        .width(300.dp)
                        .height(370.dp)
                        .align(Alignment.CenterHorizontally),
                    whereLabel = whereLabel,
                    whenLabel = whenLabel,
                    whatLabel = whatLabel,
                    selectedGatheringName = selectedGatheringName,
                    onSelectGatheringClick = onSelectGatheringClick,
                    onCloseSelectedGatheringClick = onCloseSelectedGatheringClick
                )
            }

            TukSolidButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                text = stringResource(R.string.create_proposal_propose),
                buttonType = if(isGatheringSelected) TukSolidButtonType.ACTIVE else TukSolidButtonType.DISABLED,
                onClick = {
                    // TODO
                }
            )
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CreateProposalGradientBackgroundImage(
    modifier: Modifier = Modifier
) {
    val localConfiguration = LocalConfiguration.current
    StableImage(
        modifier = modifier
            .requiredWidth(
                localConfiguration.screenWidthDp.dp * GRADIENT_BACKGROUND_IMAGE_SCALE
            ),
        contentScale = ContentScale.Crop,
        drawableResId = R.drawable.image_proposal_gradient
    )
}

@Composable
fun CreateProposalAppBar(
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

@Composable
fun CreateProposalTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp),
        text = stringResource(R.string.create_proposal_title),
        style = TukSerifTypography.title24M
    )
}


private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 3

@Preview
@Composable
private fun CreateProposalScreenPreview() {
    CreateProposalScreen(
        whereLabel = "where",
        whenLabel = "when",
        whatLabel = "what",
        selectedGatheringName = "",
        isGatheringSelected = false,
        onCloseClicked = {},
        onSelectGatheringClick = {},
        onCloseSelectedGatheringClick = {}
    )
}
