package com.plottwist.join_gathering

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.TukPrimitivesColor
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TukScaffoldTitle
import kotlinx.coroutines.launch

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun JoinGatheringScreen(
    onCloseClicked: () -> Unit = {},
    onNavigateToGatheringDetail: (Long) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    viewModel: JoinGatheringViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val localConfiguration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is JoinGatheringSideEffect.NavigateToGatheringDetail -> {
                    onNavigateToGatheringDetail(sideEffect.gatheringId)
                }

                is JoinGatheringSideEffect.ShowSnackbar -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = sideEffect.message, duration = SnackbarDuration.Short
                        )
                    }
                }

                JoinGatheringSideEffect.NavigateToLoginScreen -> {
                    onNavigateToLoginScreen()
                }
            }

        }
    }

    Box(
        modifier = Modifier
    ) {

        StableImage(
            modifier = Modifier
                .fillMaxWidth() .requiredWidth(
                    localConfiguration.screenWidthDp.dp * GRADIENT_BACKGROUND_IMAGE_SCALE
                ),
            drawableResId = R.drawable.image_home_gradient,
            contentScale = ContentScale.FillWidth
        )

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            topBar = {
                JoinGatheringAppBar(
                    onCloseClicked = onCloseClicked
                )
            },
            bottomBar = {
                JoinGatheringButton(
                    onClick = {
                        viewModel.handleAction(JoinGatheringAction.ClickJoin)
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                TukScaffoldTitle(
                    title = "모임에\n참여하시겠어요?",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                JoinGatheringContent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    gatheringName = state.gatheringName,
                    screenWidth = localConfiguration.screenWidthDp.toFloat(),
                    screenHeight = localConfiguration.screenHeightDp.toFloat()
                )
            }
        }
    }
}

@Composable
fun JoinGatheringContent(
    gatheringName: String,
    screenWidth : Float,
    screenHeight: Float,
    modifier: Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            StableImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(260f/364f),
                drawableResId = R.drawable.img_join_card,
                contentScale = ContentScale.FillWidth
            )

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "$gatheringName\n친구들에게",
                style = TukSerifTypography.title18M,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun JoinGatheringButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 17.dp)
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TukPrimitivesColor.Primary500
        ),
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "입장하기",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun JoinGatheringAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
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
fun TopAppBarCloseButton(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onCloseClicked
    ) {
        StableImage(
            drawableResId = com.plottwist.join_gathering.R.drawable.ic_close_button
        )
    }
}

private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 3

@Preview
@Composable
private fun JoinGatheringScreenPreview() {
    JoinGatheringScreen(
        onNavigateToGatheringDetail = {},
        onNavigateToLoginScreen = {}
    )
}
