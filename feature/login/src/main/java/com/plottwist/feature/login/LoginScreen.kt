package com.plottwist.feature.login

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.auth.provider.di.AuthProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Text("로그인 화면")

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Button(
            onClick = {
                viewModel.handleAction(LoginAction.ClickGoogleLogin)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues()
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = com.plottwist.core.designsystem.R.drawable.ic_user),
                    contentDescription = "Apple Login",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "로그인",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
