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
import com.plottwist.core.ui.component.StableImage

@Composable
@Preview(showBackground = true)
fun JoinGatheringScreen(
    onCloseClicked: () -> Unit = {},
    viewModel: JoinGatheringViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when(sideEffect) {
                is JoinGatheringSideEffect.NavigateToGatheringDetail ->{

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
                    .weight(1f)
            )

            Spacer(modifier = Modifier.weight(1f))
            JoinGatheringButton(
                onClick = {}
            )

        }
    }
}

@Composable
fun JoinGatheringContent(
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
                        text = "다음 만남은\n계획대로 되지 않아",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1F1F1F),
                            lineHeight = 26.sp
                        )
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
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFF6E6E6E)
                        )
                    )
                    Text(
                        text = "뜸해진 우리",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xFF6E6E6E)
                        )
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
            containerColor = Color(0xFF4B0000)
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
