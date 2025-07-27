package com.example.create_gathering

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.create_gathering.page.CreateGatheringNameInput
import com.example.create_gathering.page.CreateGatheringSelectChips
import com.example.create_gathering.page.CreateGatheringSelectFrequency
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.ui.component.StableImage

@Composable
fun CreateGatheringScreen(
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateGatheringViewModel = hiltViewModel()
) {

    val state by viewModel.container.stateFlow.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = state.currentPage,
        pageCount = { 3 }
    )

    LaunchedEffect(state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                CreateGatheringSideEffect.NavigateToHomeScreen -> {

                }

                CreateGatheringSideEffect.NavigateToBack -> {

                }
            }
        }
    }

    Scaffold(
        topBar = {
            TukTopAppBar(
                actionButtons = {
                    TopAppBarCloseButton(
                        onCloseClicked = {}
                    )
                }
            )
        },
        bottomBar = {}
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> CreateGatheringNameInput(
                    value = state.gatheringName,
                    onValueChange = viewModel::updateGatheringName,
                    onNext = viewModel::onClickNext
                )

                1 -> CreateGatheringSelectFrequency(
                    selectedOption = state.frequencyGathering,
                    onOptionSelected = {
                        viewModel.updateFrequencyGathering(it)
                        viewModel.onClickNext()
                    }
                )

                2 -> CreateGatheringSelectChips(
                    selectedTags = state.tags,
                    onToggle = { tag ->
                        viewModel.updateTags(tag)
                    },
                    onClickPrev = viewModel::onClickPrev,
                    onClickNext = viewModel::onClickNext,
                    onClickSkip = viewModel::onClickSkip
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
            drawableResId = com.example.create_gathering.R.drawable.ic_close_button
        )
    }
}
