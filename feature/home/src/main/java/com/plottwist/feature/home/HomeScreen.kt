package com.plottwist.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.feature.home.component.HomeBottomSheet
import com.plottwist.feature.home.component.HomeBottomSheetState
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
        onChangedState = {
            // 바텀 시트 펼쳐지거나 접혔을때 감지
        },
        isLoggedIn = state.isLoggedIn
    )
}

@Composable
private fun HomeScreen(
    isLoggedIn: Boolean,
    onMyPageClicked: () -> Unit,
    onChangedState: (HomeBottomSheetState) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        HomeGradientBackgroundImage(
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeAppBar(onMyPageClicked)

            HomeTitle()

            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = BOTTOM_SHEET_PEEK_HEIGHT.dp)
            )
        }
        HomeBottomSheet(
            sheetPeekHeight = BOTTOM_SHEET_PEEK_HEIGHT.dp,
            sheetFullHeight = BOTTOM_SHEET_FULL_HEIGHT.dp,
            onChangedState = onChangedState
        )
    }
}

@Composable
fun HomeAppBar(
    onMyPageClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarMyPageButton(
                onMyPageClicked = onMyPageClicked
            )
        }
    )
}

@Composable
fun HomeTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp),
        text = stringResource(R.string.home_title),
        style = TukSerifTypography.title24M,
        fontSize = 24.sp
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeGradientBackgroundImage(
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
fun TopAppBarMyPageButton(
    onMyPageClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onMyPageClicked
    ) {
        StableImage(
            drawableResId = R.drawable.ic_mypage
        )
    }
}

private const val BOTTOM_SHEET_PEEK_HEIGHT = 120
private const val BOTTOM_SHEET_FULL_HEIGHT = 570
private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 2

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        isLoggedIn = false,
        onMyPageClicked = {},
        onChangedState = {}
    )
}
