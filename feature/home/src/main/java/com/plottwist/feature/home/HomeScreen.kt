package com.plottwist.feature.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TOP_APP_BAR_HEIGHT
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.TukColorTokens.CoralRed500
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.core.ui.component.StableImage
import com.plottwist.feature.home.component.HomeBottomSheet
import com.plottwist.feature.home.component.HomeBottomSheetAction
import com.plottwist.feature.home.component.HomeBottomSheetState
import com.plottwist.feature.home.component.HomeContent
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import kotlin.math.roundToInt

@SuppressLint("ConfigurationScreenWidthHeight")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToMyPageScreen: () -> Unit,
    navigateToCreateGathering: () -> Unit,
    navigateToGatheringDetail: (Long) -> Unit,
    navigateToCreateProposal: (whereLabel: String, whenLabel: String, whatLabel: String) -> Unit,
    navigateToSelectGathering: (whereLabel: String, whenLabel: String, whatLabel: String) -> Unit,
    navigateToWebView: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()
    val configuration = LocalConfiguration.current
    val statusBarPaddingValue = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val statusBarHeight = remember(statusBarPaddingValue) { statusBarPaddingValue }
    var isShownNoGatheringsPopup by remember { mutableStateOf(false) }
    var requestNotificationPermission by remember { mutableStateOf(false) }
    var homeBottomSheetAction: HomeBottomSheetAction by remember {
        mutableStateOf(HomeBottomSheetAction.IDLE)
    }
    var hasBottomSheetShook by remember { mutableStateOf(false) }

    LaunchedEffect(homeBottomSheetAction) {
        delay(200)
        homeBottomSheetAction = HomeBottomSheetAction.IDLE
    }

    LaunchedEffect(Unit) {
        delay(800)
        hasBottomSheetShook = true
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            HomeSideEffect.NavigateToLoginScreen -> {
                navigateToLoginScreen()
            }

            HomeSideEffect.NavigateToCreateGatheringScreen -> {
                navigateToCreateGathering()
            }
            HomeSideEffect.NavigateToMyPageScreen -> {
                navigateToMyPageScreen()
            }

            is HomeSideEffect.NavigateToGatheringDetailScreen -> {
                navigateToGatheringDetail(sideEffect.gatheringId)
            }

            is HomeSideEffect.NavigateToCreateProposalScreen -> {
                navigateToCreateProposal(
                    sideEffect.whereLabel,
                    sideEffect.whenLabel,
                    sideEffect.whatLabel
                )
            }

            is HomeSideEffect.NavigateToWebViewScreen -> {
                navigateToWebView(sideEffect.encodedUrl)
            }

            HomeSideEffect.ShowNoGatheringsPopup -> {
                isShownNoGatheringsPopup = !isShownNoGatheringsPopup
            }

            HomeSideEffect.RequestNotificationPermission -> {
                requestNotificationPermission = true
            }

            is HomeSideEffect.NavigateToSelectGatheringScreen -> {
                navigateToSelectGathering(
                    sideEffect.whereLabel,
                    sideEffect.whenLabel,
                    sideEffect.whatLabel
                )
            }
        }
    }

    HomeScreen(
        modifier = modifier.fillMaxSize(),
        screenHeight = configuration.screenHeightDp.dp,
        statusBarHeight = statusBarHeight,
        userName = state.userName,
        whenLabels = state.whenTags,
        whereLabels = state.whereTags,
        whatLabels = state.whatTags,
        loginState = state.loginState,
        gatherings = state.gatherings,
        homeBottomSheetAction = homeBottomSheetAction,
        onMyPageClick = {
            viewModel.handleAction(HomeAction.ClickMyPage)
        },
        onAddGatheringClick = {
            viewModel.handleAction(HomeAction.ClickAddGathering)
        },
        onGatheringClick = { id ->
            viewModel.handleAction(HomeAction.ClickGathering(id))
        },
        onChangedState = {
            // 바텀 시트 펼쳐지거나 접혔을때 감지
        },
        onWhenRefreshClick = {
            viewModel.handleAction(HomeAction.ClickRefreshWhen)
        },
        onWhereRefreshClick = {
            viewModel.handleAction(HomeAction.ClickRefreshWhere)
        },
        onWhatRefreshClick = {
            viewModel.handleAction(HomeAction.ClickRefreshWhat)
        },
        onProposeClick = {
            viewModel.handleAction(HomeAction.ClickPropose(it))
        },
        onProposalsClick = {
            viewModel.handleAction(HomeAction.ClickProposals)
        }
    )

    if(isShownNoGatheringsPopup) {
        AlertDialog(
            text = {
                Text(
                    text = "아직 만들어진 모임이 없어요.\n" +
                            "우리, 먼저 모임부터 만들어볼까요?",
                    style = TukPretendardTypography.body14R
                )
            },
            onDismissRequest = {
                isShownNoGatheringsPopup = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isShownNoGatheringsPopup = false
                        homeBottomSheetAction = HomeBottomSheetAction.COLLAPSE
                    }
                ) {
                    Text(
                        text = stringResource(R.string.common_confirm),
                        style = TukPretendardTypography.body14M,
                        color = CoralRed500
                    )
                }
            },
        )
    }

    if(requestNotificationPermission) {
        RequestPermission(
            onPermissionsGranted = {
                requestNotificationPermission = false
                viewModel.handleAction(HomeAction.OnPermissionGranted)
            },
            onShowRationalDialog = {}
        )
    }
}

@Composable
private fun HomeScreen(
    screenHeight: Dp,
    statusBarHeight: Dp,
    userName: String,
    loginState: LoginState,
    gatherings: Gatherings,
    whenLabels: List<String>,
    whereLabels: List<String>,
    whatLabels: List<String>,
    homeBottomSheetAction: HomeBottomSheetAction,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onMyPageClick: () -> Unit,
    onAddGatheringClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    onChangedState: (HomeBottomSheetState) -> Unit,
    onProposeClick: (Int) -> Unit,
    onProposalsClick: () -> Unit,
    modifier: Modifier = Modifier,
    verticalScrollState : ScrollState = rememberScrollState()
) {
    val homeContentTopPadding = screenHeight / 2 - statusBarHeight -  TOP_APP_BAR_HEIGHT.dp
    val shake = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        delay(200)
        for (i in 0..10) {
            when (i % 2) {
                0 -> shake.animateTo(3f, spring(stiffness = 50_000f))
                else -> shake.animateTo(-3f, spring(stiffness = 50_000f))
            }
        }
        shake.animateTo(0f)
    }


    Box(
        modifier = modifier
    ) {
        HomeGradientBackgroundImage(
            modifier = Modifier.align(Alignment.Center)
        )
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            HomeAppBar(onMyPageClick)

            Column(
                modifier = Modifier.fillMaxSize().padding(
                    bottom = BOTTOM_SHEET_PEEK_HEIGHT.dp
                ).verticalScroll(verticalScrollState)
            ) {
                HomeTitle(
                    modifier = Modifier.height(HOME_TITLE_HEIGHT.dp),
                    name = userName
                )

                HomeContent(
                    modifier = Modifier
                        .padding(
                            top = homeContentTopPadding - HOME_TITLE_HEIGHT.dp - 40.dp,
                            bottom = 40.dp
                        ),
                    gatherings = gatherings,
                    onAddGatheringClick = onAddGatheringClick,
                    onGatheringClick = onGatheringClick
                )
            }
        }

        HomeBottomSheet(
            modifier = Modifier
                .offset { IntOffset(x= 0, y = shake.value.roundToInt()) }  ,
            whenLabels = whenLabels,
            whereLabels = whereLabels,
            whatLabels = whatLabels,
            sheetPeekHeight = BOTTOM_SHEET_PEEK_HEIGHT.dp,
            sheetFullHeight = BOTTOM_SHEET_FULL_HEIGHT.dp,
            homeBottomSheetAction = homeBottomSheetAction,
            onWhenRefreshClick = onWhenRefreshClick,
            onWhereRefreshClick = onWhereRefreshClick,
            onWhatRefreshClick = onWhatRefreshClick,
            onChangedState = onChangedState,
            onProposeClick = onProposeClick
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
    name: String,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(start = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.home_subtitle, name),
            style = TukSerifTypography.title22M
        )

        Text(
            text = stringResource(R.string.home_title),
            style = TukSerifTypography.title22M
        )
    }

}

@Composable
fun HomeProposals(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = stringResource(R.string.home_proposals),
            style = TukSerifTypography.body14R.copy(
                color = Gray800
            )
        )
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_next_arrow),
            contentDescription = "",
            tint = Gray800
        )
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(onPermissionsGranted: () -> Unit, onShowRationalDialog: () -> Unit) {
    val permissionAlreadyRequested = rememberSaveable {
        mutableStateOf(false)
    }

    val permissionState = rememberPermissionState(
        permission =  Manifest.permission.POST_NOTIFICATIONS,
        onPermissionResult = { result ->
            permissionAlreadyRequested.value = true

            if(result) {
                onPermissionsGranted()
            }
        }
    )

    if (!permissionAlreadyRequested.value && !permissionState.status.shouldShowRationale) {
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    } else {
        onShowRationalDialog()
    }
}

private const val HOME_TITLE_HEIGHT = 108
private const val BOTTOM_SHEET_PEEK_HEIGHT = 120
private const val BOTTOM_SHEET_FULL_HEIGHT = 570
private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 2

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        loginState = LoginState.LoggedIn,
        gatherings = Gatherings(),
        userName = "",
        onMyPageClick = {},
        onAddGatheringClick = {},
        onGatheringClick = {},
        onChangedState = {},
        whenLabels = emptyList(),
        whereLabels = emptyList(),
        whatLabels = emptyList(),
        homeBottomSheetAction = HomeBottomSheetAction.IDLE,
        onWhenRefreshClick = { },
        onWhereRefreshClick = { },
        onWhatRefreshClick = { },
        onProposeClick = {},
        onProposalsClick = {},
        screenHeight = 720.dp,
        statusBarHeight = 28.dp,
    )
}
