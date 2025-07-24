package com.plottwist.feature.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.SolidButton
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.feature.home.component.HomeBottomSheet
import com.plottwist.feature.home.component.HomeBottomSheetState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToMyPageScreen: () -> Unit,
    navigateToCreateGathering: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            HomeSideEffect.NavigateToLoginScreen -> {
                navigateToLoginScreen()
            }

            HomeSideEffect.NavigateToCreateGatheringScreen -> {
                // 임시 코드
                Toast.makeText(context, "모임 생성 화면 이동", Toast.LENGTH_SHORT).show()
            }
            HomeSideEffect.NavigateToMyPageScreen -> {
                navigateToMyPageScreen()
            }
        }
    }

    HomeScreen(
        modifier = modifier.fillMaxSize(),
        onMyPageClick = {
            viewModel.handleAction(HomeAction.ClickMyPage)
        },
        onAddGatheringClick = {
            viewModel.handleAction(HomeAction.ClickAddGathering)
        },
        onChangedState = {
            // 바텀 시트 펼쳐지거나 접혔을때 감지
        },
        loginState = state.loginState
    )
}

@Composable
private fun HomeScreen(
    loginState: LoginState,
    onMyPageClick: () -> Unit,
    onAddGatheringClick: () -> Unit,
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
            HomeAppBar(onMyPageClick)

            HomeTitle()

            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = BOTTOM_SHEET_PEEK_HEIGHT.dp),
                loginState = loginState,
                onAddGatheringClick = onAddGatheringClick
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
    onMyPageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarMyPageButton(
                onMyPageClicked = onMyPageClick
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
        style = TukSerifTypography.title24M
    )
}

@Composable
fun HomeContent(
    loginState: LoginState,
    onAddGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(loginState == LoginState.Loading) return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_bottom_create_gathering_description),
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
            textAlign = TextAlign.Center
        )
        SolidButton(
            text = stringResource(R.string.home_bottom_create_gathering_button_text),
            containerColor = Color(0xFFFF3838),
            contentColor = Color(0xFFFFFFFF),
            onClick = onAddGatheringClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                contentDescription = stringResource(R.string.home_bottom_create_gathering_button_text),
                tint = Color(0xFFFFFFFF)
            )
        }
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
        loginState = LoginState.LoggedIn,
        onMyPageClick = {},
        onAddGatheringClick = {},
        onChangedState = {}
    )
}
