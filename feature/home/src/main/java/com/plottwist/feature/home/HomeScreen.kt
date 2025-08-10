package com.plottwist.feature.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.plottwist.core.designsystem.R
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
import com.plottwist.feature.home.component.HomeCreateGatheringPreview
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    navigateToLoginScreen: () -> Unit,
    navigateToMyPageScreen: () -> Unit,
    navigateToCreateGathering: () -> Unit,
    navigateToGatheringDetail: (Long) -> Unit,
    navigateToCreateProposal: (whereLabel: String, whenLabel: String, whatLabel: String) -> Unit,
    navigateToWebView: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.collectAsState()
    var isShownNoGatheringsPopup by remember { mutableStateOf(false) }
    var requestNotificationPermission by remember { mutableStateOf(false) }
    var homeBottomSheetAction: HomeBottomSheetAction by remember {
        mutableStateOf(HomeBottomSheetAction.IDLE)
    }

    LaunchedEffect(homeBottomSheetAction) {
        delay(200)
        homeBottomSheetAction = HomeBottomSheetAction.IDLE
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
        }
    }

    HomeScreen(
        modifier = modifier.fillMaxSize(),
        whenLabel = state.whenLabel,
        whereLabel = state.whereLabel,
        whatLabel = state.whatLabel,
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
            viewModel.handleAction(HomeAction.ClickPropose)
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
    loginState: LoginState,
    gatherings: Gatherings,
    whenLabel: String,
    whereLabel: String,
    whatLabel: String,
    homeBottomSheetAction: HomeBottomSheetAction,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onMyPageClick: () -> Unit,
    onAddGatheringClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    onChangedState: (HomeBottomSheetState) -> Unit,
    onProposeClick: () -> Unit,
    onProposalsClick: () -> Unit,
    modifier: Modifier = Modifier,
    verticalScrollState : ScrollState = rememberScrollState()
) {
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
                HomeTitle()

                if(loginState == LoginState.Loading) return

                HomeContent(
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 40.dp),
                    gatherings = gatherings,
                    onAddGatheringClick = onAddGatheringClick,
                    onGatheringClick = onGatheringClick
                )
            }
        }

        HomeBottomSheet(
            whenLabel = whenLabel,
            whereLabel = whereLabel,
            whatLabel = whatLabel,
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
    name: String = "",
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp, bottom = 18.dp),
        text = stringResource(R.string.home_subtitle, name),
        style = TukSerifTypography.title22M
    )

    Text(
        modifier = modifier.padding(start = 20.dp),
        text = stringResource(R.string.home_title),
        style = TukSerifTypography.title22M
    )
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
        onMyPageClick = {},
        onAddGatheringClick = {},
        onGatheringClick = {},
        onChangedState = {},
        whenLabel = "",
        whereLabel = "",
        whatLabel = "",
        homeBottomSheetAction = HomeBottomSheetAction.IDLE,
        onWhenRefreshClick = { },
        onWhereRefreshClick = { },
        onWhatRefreshClick = { },
        onProposeClick = {},
        onProposalsClick = {}
    )
}
