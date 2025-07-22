package com.plottwist.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.ui.StableImage
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            HomeSideEffect.NavigateToLoginScreen -> {
                navigateToLoginScreen()
            }
        }
    }

    HomeScreen(
        modifier = modifier.fillMaxSize(),
        onMyPageClicked = {
            viewModel.handleAction(HomeAction.ClickMyPage)
        },
        isLoggedIn = state.isLoggedIn
    )
}

@Composable
private fun HomeScreen(
    isLoggedIn: Boolean,
    onMyPageClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        StableImage(
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onMyPageClicked() },
            drawableResId = R.drawable.ic_user
        )
    }
}
