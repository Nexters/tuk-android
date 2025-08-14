package com.plottwist.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukButton
import com.plottwist.core.designsystem.component.TukRoundSolidButton
import com.plottwist.core.designsystem.foundation.TukColorTokens.CoralRed100
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun RandomProposal(
    isPlayed: Boolean,
    whenLabels: List<String>,
    whereLabels: List<String>,
    whatLabels: List<String>,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onProposeClick: (Int) -> Unit,
    onStopClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val minSize = arrayOf(whatLabels.size, whereLabels.size, whenLabels.size).min()

    LaunchedEffect(lifecycleOwner, isPlayed, minSize) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            while (isActive && isPlayed && minSize >= 1) {
                delay(DURATION_SHOW_ITEM)

                delay(DURATION_ANIMATION.toLong())
                currentIndex = (currentIndex + 1) % minSize
            }
        }
    }

    Column (
        modifier = modifier.padding(bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(R.string.home_random_proposal_description_prefix),
            style = TukSerifTypography.title18M,
            color = Gray900,
            textAlign = TextAlign.Center
        )

        RandomProposalItem(
            labels = whereLabels,
            currentIndex = currentIndex,
            onRefreshClick = onWhereRefreshClick
        )

        RandomProposalItem(
            labels = whenLabels,
            currentIndex = currentIndex,
            onRefreshClick = onWhenRefreshClick
        )

        RandomProposalItem(
            labels = whatLabels,
            currentIndex = currentIndex,
            onRefreshClick = onWhatRefreshClick
        )

        Text(
            text = stringResource(R.string.home_random_proposal_description_suffix),
            style = TukSerifTypography.title18M,
            color = Gray900,
        )

        Spacer(modifier = Modifier.weight(1f))

        if(isPlayed) {
            StopButton(
                onClick = onStopClick
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TukButton(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    contentColor = Gray900,
                    disabledContentColor = Gray900,
                    border = BorderStroke(1.dp, Gray900),
                    shape = CircleShape,
                    height = 40.dp  ,
                    onClick = onPlayClick,
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_refresh),
                        contentDescription = "refresh",
                        tint = Gray900
                    )
                }
                TukRoundSolidButton(
                    text = stringResource(R.string.home_bottom_sheet_nudging_text),
                    modifier = Modifier.height(40.dp),
                    containerColor = Gray900,
                    contentColor = Color(0xFFFFFFFF),
                    onClick = {
                        onProposeClick(currentIndex)
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_next_arrow_circle),
                        contentDescription = "propose",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            }

        }
    }
}

@Composable
fun RandomProposalItem(
    currentIndex: Int,
    labels: List<String>,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .width(284.dp)
            .height(52.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = gradientColors,
                    radius = 240f
                ),
                shape = CircleShape
            )
            .clip(CircleShape)
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = labels.getOrNull(currentIndex) ?: "",
            style = TukSerifTypography.body16M,
            textAlign = TextAlign.Center,
            color = Gray900
        )
    }
}

@Composable
fun StopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Gray900
) {
    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(100)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100)
            )
            .clickable {
                onClick()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp
            )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_stop),
            contentDescription = "stop",
            tint = Gray000
        )
        Text(
            text = stringResource(R.string.home_bottom_sheet_stop_text),
            style = TukPretendardTypography.body14M,
            color = Gray000
        )
    }
}

private const val DURATION_SHOW_ITEM = 30L
private const val DURATION_ANIMATION = 30

private val gradientColors = listOf(
    Color(0xFFFFA7A7),
    Color(0xFFFFDBDB),
)
