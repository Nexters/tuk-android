package com.plottwist.feature.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            MyPageSideEffect.NavigateToEditName -> {

            }
            MyPageSideEffect.NavigateToNotificationSetting-> {

            }
            MyPageSideEffect.NavigateToUpdateApp-> {

            }
            MyPageSideEffect.NavigateToTerms-> {

            }
            MyPageSideEffect.NavigateToPrivacyPolicy-> {

            }
            MyPageSideEffect.NavigateToLogout->{

            }
        }
    }

    MyPageContent(
        onEditNameClick = { viewModel.onAction(MyPageAction.ClickEditName) },
        onNotificationClick = { viewModel.onAction(MyPageAction.ClickNotificationSetting) },
        onUpdateClick = { viewModel.onAction(MyPageAction.ClickUpdateApp) },
        onTermsClick = { viewModel.onAction(MyPageAction.ClickTerms) },
        onPrivacyPolicyClick = { viewModel.onAction(MyPageAction.ClickPrivacyPolicy) },
        onLogoutClick = { viewModel.onAction(MyPageAction.ClickLogout) },
        onBackClick = onBackClick
    )

}

@Composable
fun MyPageContent(
    onEditNameClick: () ->Unit,
    onNotificationClick: () ->Unit,
    onUpdateClick: () -> Unit,
    onTermsClick:() ->Unit,
    onPrivacyPolicyClick:() ->Unit,
    onLogoutClick:() ->Unit,
    onBackClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TukTopAppBar(
                type = TukTopAppBarType.DEPTH,
                title = "마이페이지",
                onBack = onBackClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
            ) {
                item {
                    SectionTitle("내 정보관리")
                    MyPageItem("이름 설정",onEditNameClick)
                    MyPageItem("알림 설정",onNotificationClick)
                }

                item {
                    SectionTitle("ABOUT TUK")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick =onUpdateClick )
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "앱 버전", style = TukPretendardTypography.body16R )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "1.2.0", color = Color(0xFFCCCCCC), style = TukPretendardTypography.body16R)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "업데이트하기", color = Color(0xFF888888), style = TukPretendardTypography.body14R )
                        }

                    }
                    MyPageItem("서비스 이용약관",onTermsClick)
                    MyPageItem("개인정보 처리방침",onPrivacyPolicyClick)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    MyPageItem("로그아웃",onLogoutClick)
                }


            }
        }
    }

}

@Composable
fun SectionTitle(text:String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top= 20.dp, bottom = 8.dp),
        style = TukPretendardTypography.body14R
    )
}

@Composable
fun MyPageItem(
    title:String,
    onClick: () ->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            style = TukPretendardTypography.body16R
        )
    }
}

