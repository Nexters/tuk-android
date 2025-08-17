package com.plottwist.feature.login

import android.app.Activity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.auth.provider.di.AuthProviderEntryPoint
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TopAppBarCloseButton
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    navigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity
    val googleAuthProvider = remember {
        EntryPointAccessors.fromActivity(
            activity,
            AuthProviderEntryPoint::class.java
        ).googleAuthProvider()
    }

    var postCardAnimated by remember { mutableStateOf(false) }
    val postCardPadding by animateDpAsState(
        targetValue = if(postCardAnimated) 56.dp else 0.dp,
        animationSpec = tween(700)
    )

    LaunchedEffect(Unit) {
        delay(500)
        postCardAnimated = true
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            LoginSideEffect.GoogleLogin -> {
                googleAuthProvider.login(
                    context = context,
                    onSuccess = {
                        viewModel.handleAction(LoginAction.OnGoogleLoginSuccess(it.idToken))
                    },
                    onError = {
                        viewModel.handleAction(LoginAction.OnGoogleLoginError(it.message))
                    }
                )
            }

            LoginSideEffect.NavigateToHomeScreen -> {
                onBack()
            }

            LoginSideEffect.NavigateToOnboardingScreen -> {
                navigateToOnboarding()
            }
        }
    }

    LoginScreen(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        postCardPadding = postCardPadding,
        onCloseClicked = onBack,
        onGoogleLoginButtonClick = {
            viewModel.handleAction(LoginAction.ClickGoogleLogin)
        }
    )
}

@Composable
private fun LoginScreen(
    postCardPadding : Dp,
    onCloseClicked: () -> Unit,
    onGoogleLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginAppBar(
                onCloseClicked = onCloseClicked
            )

            Text(
                modifier = Modifier.padding(top = 48.dp, bottom = 20.dp),
                text = "누군가 나를 떠올리며 \n" +
                        "툭, 건넸어요",
                textAlign = TextAlign.Center,
                style = TukSerifTypography.body16M
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                StableImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    drawableResId = R.drawable.img_login_card,
                    contentScale = ContentScale.FillWidth
                )
            }


            GoogleLoginButtonColumn(
                modifier = Modifier.fillMaxWidth(),
                onGoogleLoginButtonClick = onGoogleLoginButtonClick
            )
        }
    }

}

@Composable
fun LoginAppBar(
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
fun LoginContent(
    postCardPadding : Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        PostCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp + postCardPadding)
                .padding(horizontal = 62.dp)
                .aspectRatio(260f / 334f)
        )

        StableImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 62.dp)
                .aspectRatio(260f / 364f),
            drawableResId = R.drawable.image_post_cover,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun PostCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp)
) {
    Column (
        modifier = modifier
            .clip(shape)
            .border(
                width = 1.dp,
                color = Color(0xFF000000).copy(0.05f),
                shape = shape
            )
            .background(
                color = Color(0xFFFAFAFA),
                shape = shape
            )
            .padding(top = 25.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StableImage(
            drawableResId = R.drawable.image_double_quotation_marks
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.post_card_bottom_description_start),
                style = TukSerifTypography.body14R.copy(
                    fontSize = 12.sp
                ),
                color = Color(0xFF1F1F1F)
            )

            Text(
                text = stringResource(R.string.post_card_bottom_description_end),
                style = TukSerifTypography.body14R.copy(
                    fontSize = 12.sp
                ),
                color = Color(0xFF1F1F1F)
            )
        }
    }
}

@Composable
fun GoogleLoginButtonColumn(
    onGoogleLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_description),
            style = TukPretendardTypography.body12R,
            color = Color(0xFF888888),
            textAlign = TextAlign.Center
        )

        GoogleLoginButton(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 12.dp, bottom = 20.dp)
                .fillMaxWidth()
                .height(48.dp),
            onClick = onGoogleLoginButtonClick
        )
    }
}


@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(100)
) {
    Row(
        modifier = modifier
            .widthIn(max = 315.dp)
            .clip(shape)
            .background(
                color = Color(0xFF131314)
            )
            .border(
                width = 1.dp,
                color = Color(0xFF8E918F),
                shape = shape
            )
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.85.dp, Alignment.CenterHorizontally)
    ) {
        StableImage(
            modifier = Modifier.size(24.dp),
            drawableResId = R.drawable.image_google
        )
        Text(
            text = stringResource(R.string.login_google_button_text),
            style = TukPretendardTypography.body16R.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color(0xFFE3E3E3)
        )
    }
}
