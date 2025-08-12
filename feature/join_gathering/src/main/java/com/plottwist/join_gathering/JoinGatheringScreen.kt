package com.plottwist.join_gathering

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray300
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray500
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray700
import com.plottwist.core.designsystem.foundation.TukPrimitivesColor
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage

@Composable
fun JoinGatheringScreen(
    onCloseClicked: () -> Unit = {},
    onNavigateToGatheringDetail : (Long) -> Unit,
    viewModel: JoinGatheringViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when(sideEffect) {
                is JoinGatheringSideEffect.NavigateToGatheringDetail ->{
                    onNavigateToGatheringDetail(sideEffect.gatheringId)
                }
            }

        }
    }

    Box(
        modifier = Modifier
    ) {
        StableImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd),
            drawableResId = R.drawable.image_join_gathering_gradient,
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JoinGatheringAppBar(
                onCloseClicked = onCloseClicked
            )

            StableImage(
                modifier = Modifier.padding(horizontal = 20.dp),
                drawableResId = R.drawable.image_join_gathering_title
            )
            JoinGatheringContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp)
                    .weight(1f),
                gatheringName = state.gatheringName
            )

            Spacer(modifier = Modifier.weight(1f))
            JoinGatheringButton(
                onClick = {
                    viewModel.handleAction(JoinGatheringAction.ClickJoin)
                }
            )

        }
    }
}

@Composable
fun JoinGatheringContent(
    gatheringName: String,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(288f / 349f),
    ) {
        StableImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            drawableResId = R.drawable.image_join_gathering_card
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            StableImage(
                modifier = Modifier.fillMaxSize(),
                drawableResId = R.drawable.image_join_gathering_card,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 36.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 63.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = gatheringName,
                        textAlign = TextAlign.Center,
                        style = TukSerifTypography.title22M
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 38.dp, end = 38.dp, bottom = 33.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "연락이",
                        style = TukSerifTypography.body14R,
                        color = Gray700
                    )
                    Text(
                        text = "뜸해진 우리",
                        style = TukSerifTypography.body14R,
                        color = Gray700
                    )
                }
            }
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
