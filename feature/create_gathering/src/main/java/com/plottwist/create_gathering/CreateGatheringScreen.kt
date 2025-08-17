package com.plottwist.create_gathering

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.create_gathering.page.CreateGatheringNameInput
import com.plottwist.create_gathering.page.CreateGatheringSelectIntervalDays
import com.plottwist.create_gathering.page.CreateGatheringSelectTags
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.ui.component.StableImage
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringScreen(
    onBack: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateGatheringViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    val pagerState = rememberPagerState(
        initialPage = state.currentPage,
        pageCount = { 2 }
    )

    BackHandler {
        if(pagerState.currentPage == 0) {
            onBack()
        } else {
            viewModel.onAction(CreateGatheringAction.ClickPrev)
        }
    }

    LaunchedEffect(state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                CreateGatheringSideEffect.NavigateToHomeScreen -> {
                    Toast.makeText(
                        context,
                        "모임이 생성되었어요!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToHomeScreen()
                }

                CreateGatheringSideEffect.NavigateToBack -> {
                    onBack()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TukTopAppBar(
            actionButtons = {
                TopAppBarCloseButton(onCloseClicked = {
                    viewModel.onAction(CreateGatheringAction.ClickClose)
                })
            }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> CreateGatheringNameInput(
                    state = state.gatheringName,
                    onNext = {
                        viewModel.onAction(CreateGatheringAction.ClickNext)
                    },
                    onClear = {
                        viewModel.onAction(CreateGatheringAction.ClearGatheringName)
                    }
                )

                1 -> CreateGatheringSelectIntervalDays(
                    selectedOption = state.intervalDays,
                    onClickPrev = { viewModel.onAction(CreateGatheringAction.ClickPrev) },
                    onOptionSelected = {
                        viewModel.onAction(CreateGatheringAction.UpdateIntervalDays(it))
                    },
                    onNext = {
                        viewModel.onAction(CreateGatheringAction.SubmitGathering)
                    }
                )
            }
        }
    }


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
            drawableResId = R.drawable.ic_close_button
        )
    }
}
