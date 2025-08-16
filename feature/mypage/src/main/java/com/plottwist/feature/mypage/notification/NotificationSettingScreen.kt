package com.plottwist.feature.mypage.notification

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray500
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.feature.mypage.R
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun NotificationSettingScreen(
    viewModel: NotificationSettingViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    LifecycleStartEffect(Unit) {
        viewModel.checkNotificationStatus()
        onStopOrDispose { }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NotificationSettingSideEffect.OpenNotificationSettings -> {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
                context.startActivity(intent)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TukTopAppBar(
            type = TukTopAppBarType.DEPTH,
            title = "알림 설정",
            onBack = { onBack() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        ) {
            Text(
                text = if (state.areNotificationsEnabled) "기기 알림이\n켜져있어요" else "기기 알림이\n꺼져있어요",
                style = TukSerifTypography.title22M,
                color = Color(0xFF1F1F1F)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (state.areNotificationsEnabled)
                    "친구들의 만남 제안을 놓치지 않도록\n알림을 보내드릴게요."
                else
                    "친구들의 만남 제안을 놓치지 않기 위해\n알림 허용이 필요해요",
                style = TukPretendardTypography.body14R,
                color = Color(0xFF888888),
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { viewModel.onClickNotificationButton() },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if(!state.areNotificationsEnabled) {
                            Color(0xFFE74C3C)
                        } else {
                            Gray500
                        }
                )
            ) {
                Text(
                    text = if(!state.areNotificationsEnabled) {
                        "기기 알림 켜기"
                    } else {
                        "기기 알림 끄기"
                    },
                    color = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_next),
                    contentDescription = "화살표 아이콘",
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }
}
