package com.plottwist.feature.login

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.auth.provider.di.AuthProviderEntryPoint
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage
import dagger.hilt.android.EntryPointAccessors
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity
    val coroutineScope = rememberCoroutineScope()
    val googleAuthProvider = remember {
        EntryPointAccessors.fromActivity(
            activity,
            AuthProviderEntryPoint::class.java
        ).googleAuthProvider()
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
            LoginSideEffect.NavigateToMyPage -> {
                // TODO
            }
        }
    }

    LoginScreen(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        onCloseClicked = onBack,
        onGoogleLoginButtonClick = {
            viewModel.handleAction(LoginAction.ClickGoogleLogin)
        }
    )
}

@Composable
private fun LoginScreen(
    onCloseClicked: () -> Unit,
    onGoogleLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
    ) {
        StableImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterEnd),
            drawableResId = R.drawable.image_login_gradient,
            contentScale = ContentScale.FillWidth
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginAppBar(
                onCloseClicked = onCloseClicked
            )
            StableImage(
                drawableResId = R.drawable.image_app_title
            )

            LoginContent(
                modifier = Modifier.weight(1f)
            )

            GoogleLoinButtonColumn(
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
fun TopAppBarCloseButton(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onCloseClicked
    ) {
        StableImage(
            drawableResId = R.drawable.ic_close
        )
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {

    }
}

@Composable
fun GoogleLoinButtonColumn(
    onGoogleLoginButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
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
