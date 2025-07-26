package com.plottwist.feature.login

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.auth.provider.di.AuthProviderEntryPoint
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
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
        onCloseClicked = onBack
    )
}

@Composable
private fun LoginScreen(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        LoginAppBar(
            onCloseClicked = onCloseClicked
        )

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
