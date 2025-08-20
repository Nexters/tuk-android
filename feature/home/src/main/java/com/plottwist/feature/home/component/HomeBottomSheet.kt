package com.plottwist.feature.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.extension.borderExceptBottom
import com.plottwist.core.ui.extension.dropShadow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun HomeBottomSheet(
    selectedCurrentIndex: Int,
    whenLabels: List<String>,
    whereLabels: List<String>,
    whatLabels: List<String>,
    sheetPeekHeight: Dp,
    sheetFullHeight: Dp,
    homeBottomSheetAction: HomeBottomSheetAction,
    isPlayed: Boolean,
    onChangedState: (HomeBottomSheetState) -> Unit,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onProposeClick: (Int) -> Unit,
    onStopClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    DraggableBottomSheet(
        modifier = modifier,
        selectedCurrentIndex = selectedCurrentIndex,
        isPlayed = isPlayed,
        whenLabels = whenLabels,
        whereLabels = whereLabels,
        whatLabels = whatLabels,
        homeBottomSheetAction = homeBottomSheetAction,
        onWhenRefreshClick = onWhenRefreshClick,
        onWhereRefreshClick = onWhereRefreshClick,
        onWhatRefreshClick = onWhatRefreshClick,
        sheetPeekHeight = sheetPeekHeight,
        sheetFullHeight = sheetFullHeight,
        onChangedState = onChangedState,
        onProposeClick = onProposeClick,
        onStopClick = onStopClick,
        onPlayClick = onPlayClick,
    )
}

@Composable
fun DraggableBottomSheet(
    modifier: Modifier = Modifier,
    isPlayed: Boolean,
    selectedCurrentIndex: Int,
    whenLabels: List<String>,
    whereLabels: List<String>,
    whatLabels: List<String>,
    sheetPeekHeight: Dp,
    sheetFullHeight: Dp,
    homeBottomSheetAction: HomeBottomSheetAction,
    onChangedState: (HomeBottomSheetState) -> Unit,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onProposeClick: (Int) -> Unit,
    onStopClick: () -> Unit,
    onPlayClick: () -> Unit,
    thresholdHeight: Dp = 30.dp,
    shape: RoundedCornerShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    borderColor: Color = Color(0xAAE1E1E1),
    backgroundColor: Color = Color(0xFFFCFCFC)
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    val sheetPeekPx = with(density) { sheetPeekHeight.toPx() }
    val sheetFullPx = with(density) { sheetFullHeight.toPx() }
    val thresholdPx = with(density) { thresholdHeight.toPx() }

    val savedOffset = rememberSaveable { mutableStateOf(sheetFullPx - sheetPeekPx) }
    val offsetY = remember {
        Animatable(savedOffset.value)
    }

    LaunchedEffect(offsetY.value) {
        savedOffset.value = offsetY.value
    }

    var dragDelta by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        snapshotFlow { offsetY.value }
            .map { offset ->
                when (offset) {
                    0f -> HomeBottomSheetState.EXPANDED
                    sheetFullPx - sheetPeekPx -> HomeBottomSheetState.COLLAPSED
                    else -> HomeBottomSheetState.CHANGING
                }
            }
            .distinctUntilChanged()
            .collect {
                onChangedState(it)
            }
    }

    LaunchedEffect(homeBottomSheetAction) {
        when(homeBottomSheetAction) {
            HomeBottomSheetAction.EXPAND -> {
                offsetY.animateTo(0f)
            }
            HomeBottomSheetAction.COLLAPSE -> {
                offsetY.animateTo(sheetFullPx - sheetPeekPx)
            }
            else -> Unit
        }
    }

    Box(
        modifier = modifier.fillMaxSize().background(
            color = Gray800.copy(
                alpha = 0.7f * ( sheetFullPx - sheetPeekPx - offsetY.value) / (sheetFullPx - sheetPeekPx)
            )
        ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Spacer(
            modifier = Modifier.fillMaxSize()
                .then(
                    if(offsetY.value == 0f) Modifier.clickable(
                        interactionSource = null,
                        indication = null
                    ) {
                        coroutineScope.launch {
                            offsetY.animateTo(sheetFullPx - sheetPeekPx)
                        }
                    } else {
                        Modifier
                    }
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(sheetFullHeight)
                .offset { IntOffset(x = 0, y = offsetY.value.toInt()) }
                .dropShadow(
                    offsetY = (-6).dp,
                    spread = 3.dp,
                    blur = 8.dp,
                    shape = shape
                )
                .borderExceptBottom(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape
                )
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .clickable(
                    enabled = offsetY.value >= (sheetFullPx - sheetPeekPx) / 2,
                    interactionSource = null,
                    indication = null
                ) {
                    coroutineScope.launch {
                        offsetY.animateTo(0f)
                    }
                }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragStart = {
                            dragDelta = 0f
                        },
                        onVerticalDrag = { change, dragAmount ->
                            change.consume()
                            dragDelta += dragAmount

                            val newOffset =
                                (offsetY.value + dragAmount).coerceIn(0f, sheetFullPx - sheetPeekPx)
                            coroutineScope.launch {
                                offsetY.snapTo(newOffset)
                            }
                        },
                        onDragEnd = {
                            coroutineScope.launch {
                                val isExpanded = offsetY.value < (sheetFullPx - sheetPeekPx)

                                if (isExpanded) {
                                    if (dragDelta > thresholdPx) {
                                        offsetY.animateTo(sheetFullPx - sheetPeekPx)
                                    } else {
                                        offsetY.animateTo(0f)
                                    }
                                } else {
                                    if (dragDelta < -thresholdPx) {
                                        offsetY.animateTo(0f)
                                    } else {
                                        offsetY.animateTo(sheetFullPx - sheetPeekPx)
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DragHandle()

                Column (
                    modifier = Modifier
                        .padding(top = sheetPeekHeight / 2 - 20.dp)
                        .height(20.dp)
                )  {
                    AnimatedVisibility(
                        visible = offsetY.value > (sheetFullPx - sheetPeekPx) * 2 / 3,
                        enter = fadeIn(tween(200)),
                        exit = fadeOut(tween(200))
                    ) {
                        Text(
                            text = stringResource(R.string.home_bottom_sheet_nudging_text),
                            style = TukSerifTypography.body14R
                        )
                    }
                }

                AnimatedVisibility(
                    visible = offsetY.value <= (sheetFullPx - sheetPeekPx) * 2 / 3,
                    enter = fadeIn(tween(200)),
                    exit = fadeOut(tween(200))
                ) {
                    RandomProposal(
                        modifier = Modifier.padding(top = 4.dp),
                        isPlayed = isPlayed,
                        selectedCurrentIndex = selectedCurrentIndex,
                        whenLabels = whenLabels,
                        whereLabels = whereLabels,
                        whatLabels = whatLabels,
                        onWhenRefreshClick = onWhenRefreshClick,
                        onWhereRefreshClick = onWhereRefreshClick,
                        onWhatRefreshClick = onWhatRefreshClick,
                        onProposeClick = onProposeClick,
                        onStopClick = onStopClick,
                        onPlayClick = onPlayClick,
                    )
                }
            }
        }
    }
}

@Composable
fun DragHandle(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(4.dp)
            )
            .width(35.dp)
            .height(4.dp)
    )
}

enum class HomeBottomSheetState {
    EXPANDED, CHANGING, COLLAPSED
}

enum class HomeBottomSheetAction {
    EXPAND, COLLAPSE, IDLE
}
