package com.plottwist.feature.proposal_create

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukOutlinedButton
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray100
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.navigation.NavigationConstants.KEY_SELECTED_GATHERING
import com.plottwist.feature.proposal_create.component.CreateProposalPostCard
import com.plottwist.feature.proposal_create.model.SelectedGatheringParam
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CreateProposalScreen(
    backStackEntry: NavBackStackEntry,
    onBack : () -> Unit,
    navigateToSelectGatheringScreen: (Long?) -> Unit,
    navigateToCompleteProposeScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateProposalViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val selectedGatheringParam = backStackEntry.savedStateHandle.get<SelectedGatheringParam>(KEY_SELECTED_GATHERING)

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            CreateProposalSideEffect.NavigateBack -> {
                onBack()
            }

            is CreateProposalSideEffect.NavigateToSelectGathering -> {
                navigateToSelectGatheringScreen(sideEffect.selectedGatheringId)
            }

            is CreateProposalSideEffect.NavigateToCompletePropose -> {
                navigateToCompleteProposeScreen(sideEffect.encodedUrl)
            }
        }
    }

    LaunchedEffect(selectedGatheringParam) {
        if(selectedGatheringParam?.id != null && selectedGatheringParam.name != null) {
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
        screenWidth = screenWidth,
        screenHeight = configuration.screenHeightDp,
        whereLabel = state.whereLabel,
        whenLabel = state.whenLabel,
        whatLabel = state.whatLabel,
        selectedGatheringName = state.selectedGathering?.name ?: "",
        isGatheringSelected = state.selectedGathering != null,
        onBackClicked = {
            viewModel.handleAction(CreateProposalAction.ClickBack)
        },
        onSelectGatheringClick = {
            viewModel.handleAction(CreateProposalAction.ClickSelectGathering)
        },
        onCloseSelectedGatheringClick = {
            viewModel.handleAction(CreateProposalAction.ClickCloseSelectedGathering)
        },
        onProposeClick = {
            viewModel.handleAction(CreateProposalAction.ClickPropose)
        }
    )
}

@Composable
private fun CreateProposalScreen(
    screenWidth : Int,
    screenHeight: Int,
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
    selectedGatheringName: String,
    isGatheringSelected: Boolean,
    onBackClicked: () -> Unit,
    onSelectGatheringClick: () -> Unit,
    onCloseSelectedGatheringClick: () -> Unit,
    onProposeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var postCardAnimated by remember { mutableStateOf(false) }
    var postCardAnimated2 by remember { mutableStateOf(false) }
    val animatedOffset = animateDpAsState(
        targetValue = if(!postCardAnimated) 0.dp else ((60f * screenHeight) / 812f).dp,
        animationSpec = tween(500)
    )

    val animatedOffset2 = animateDpAsState(
        targetValue = if(!postCardAnimated2) 0.dp else ((180f * screenHeight) / 812f).dp,
        animationSpec = tween(700)
    )

    val topPadding = (screenHeight * 55 / 812f).dp

    LaunchedEffect(Unit) {
        delay(1000)
        postCardAnimated = true
        delay(1500)
        postCardAnimated2 = true
    }
    Box(
        modifier = modifier.fillMaxSize().background(color = Gray000).clipToBounds()
    ) {
        CreateProposalGradientBackgroundImage()
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CreateProposalAppBar(onBackClicked = onBackClicked)

            CreateProposalTitle()

            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.TopCenter
            ) {
                CreateProposalPostCard(
                    modifier = Modifier
                        .padding(top = (73f * screenHeight / 812f).dp)
                        .padding(horizontal = (60 * screenWidth / 375).dp)
                        .aspectRatio(260f/334f),
                    whereLabel = whereLabel,
                    whenLabel = whenLabel,
                    whatLabel = whatLabel,
                    selectedGatheringName = selectedGatheringName,
                    onSelectGatheringClick = onSelectGatheringClick,
                    onCloseSelectedGatheringClick = onCloseSelectedGatheringClick
                )

                CreateProposalPostCard(
                    modifier = Modifier
                        .padding(horizontal = (60 * screenWidth / 375).dp)
                        .padding(top = (73f * screenHeight / 812f).dp, bottom = 10.dp)
                        .aspectRatio(260f/334f)
                        .drawWithContent {
                            clipRect(top = topPadding.toPx() + animatedOffset.value.toPx() + animatedOffset2.value.toPx()) {
                                this@drawWithContent.drawContent()
                            }
                        }
                        .blur(10.dp, 10.dp, BlurredEdgeTreatment.Unbounded),
                    whereLabel = whereLabel,
                    whenLabel = whenLabel,
                    whatLabel = whatLabel,
                    selectedGatheringName = selectedGatheringName,
                    onSelectGatheringClick = onSelectGatheringClick,
                    onCloseSelectedGatheringClick = onCloseSelectedGatheringClick
                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .offset(y= (10f * screenHeight / 812f).dp + animatedOffset2.value + animatedOffset.value)
                ) {
                    StableImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(260f/364f),
                        drawableResId = R.drawable.image_post_cover,
                        contentScale = ContentScale.FillWidth
                    )

                    Text(
                        modifier = Modifier.align(Alignment.BottomCenter).padding(
                            bottom = (140f * screenHeight / 812f).dp
                        ),
                        text = if(selectedGatheringName.isBlank()) "연락이 뜸해진\n친구들에게"
                            else "$selectedGatheringName\n친구들에게",
                        style = TukSerifTypography.body16M,
                        textAlign = TextAlign.Center
                    )
                }

            }

            Column (
                modifier = Modifier.fillMaxWidth()
                    .then(
                        if(postCardAnimated2) {
                            Modifier.background(Gray000)
                        } else {
                            Modifier
                        }
                    )
            ){
                AnimatedVisibility(postCardAnimated2) {
                    HorizontalDivider(
                        color = Gray100
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    TukOutlinedButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.common_previous),
                        onClick = onBackClicked
                    )

                    TukSolidButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.create_proposal_propose),
                        onClick = onProposeClick
                    )
                }


            }
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
        drawableResId = R.drawable.image_home_gradient
    )
}

@Composable
fun CreateProposalAppBar(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = "만남 초대장 만들기",
        onBack = onBackClicked
    )
}

@Composable
fun CreateProposalTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp),
        text = stringResource(R.string.create_proposal_title),
        style = TukSerifTypography.title22M
    )
}




private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 3

@Preview
@Composable
private fun CreateProposalScreenPreview() {
    CreateProposalScreen(
        screenWidth = 375,
        screenHeight = 812,
        whereLabel = "where",
        whenLabel = "when",
        whatLabel = "what",
        selectedGatheringName = "다음 만남은 계획대로 되지 않아",
        isGatheringSelected = false,
        onBackClicked = {},
        onSelectGatheringClick = {},
        onCloseSelectedGatheringClick = {},
        onProposeClick = {}
    )
}
