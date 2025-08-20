package com.plottwist.feature.mypage

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.TukColorTokens.CoralRed500
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToNotificationSetting: () -> Unit,
    navigateToTerms: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToEditName: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()

    var showLogoutDialog by remember { mutableStateOf(false)}
    var showDeleteAccountDialog by remember { mutableStateOf(false) }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            MyPageSideEffect.NavigateToEditName -> {
                navigateToEditName()
            }

            MyPageSideEffect.NavigateToNotificationSetting -> {
                navigateToNotificationSetting()
            }

            MyPageSideEffect.NavigateToUpdateApp -> {

            }

            MyPageSideEffect.NavigateToTerms -> {
                navigateToTerms()
            }

            MyPageSideEffect.NavigateToPrivacyPolicy -> {
                navigateToPrivacyPolicy()
            }

            MyPageSideEffect.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    MyPageContent(
        appVersion = state.appVersionName,
        onEditNameClick = { viewModel.onAction(MyPageAction.ClickEditName) },
        onNotificationClick = { viewModel.onAction(MyPageAction.ClickNotificationSetting) },
        onUpdateClick = { viewModel.onAction(MyPageAction.ClickUpdateApp) },
        onTermsClick = { viewModel.onAction(MyPageAction.ClickTerms) },
        onPrivacyPolicyClick = { viewModel.onAction(MyPageAction.ClickPrivacyPolicy) },
        onLogoutClick = { showLogoutDialog = true },
        onDeleteAccountClick = { showDeleteAccountDialog = true },
        onBackClick = onBackClick
    )

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            text = {
                Text(
                    text = "정말 로그아웃 하시겠어요?",
                    style = TukPretendardTypography.body14R
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        viewModel.onAction(MyPageAction.ClickLogout)
                    }
                ) {
                    Text(
                        text = "확인",
                        style = TukPretendardTypography.body14M,
                        color = CoralRed500
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text(text = "취소", style = TukPretendardTypography.body14M)
                }
            }
        )
    }

    // 탈퇴 다이얼로그
    if (showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteAccountDialog = false },
            text = {
                Text(
                    text = "정말 탈퇴하시겠어요? 탈퇴 후 데이터는 복구할 수 없습니다.",
                    style = TukPretendardTypography.body14R
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteAccountDialog = false
                        viewModel.onAction(MyPageAction.ClickDeleteAccount)
                    }
                ) {
                    Text(
                        text = "확인",
                        style = TukPretendardTypography.body14M,
                        color = CoralRed500
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteAccountDialog = false }) {
                    Text(text = "취소", style = TukPretendardTypography.body14M)
                }
            }
        )
    }

}

@Composable
fun MyPageContent(
    appVersion: String,
    onEditNameClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
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
                    .padding(top = 8.dp, bottom = 60.dp)
            ) {
                item {
                    SectionTitle("내 정보관리")
                    MyPageItem("닉네임 설정", onEditNameClick)
                    MyPageItem("알림 설정", onNotificationClick)
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp)
                            .height(8.dp)
                            .background(Color(0xFFFaFaFa))
                    )
                }


                item {
                    SectionTitle("ABOUT TUK")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = false, onClick = onUpdateClick)
                            .padding(horizontal = 20.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "앱 버전", style = TukPretendardTypography.body16R)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = appVersion,
                            color = Color(0xFFCCCCCC),
                            style = TukPretendardTypography.body16R
                        )
                    }
                    MyPageItem("서비스 이용약관", onTermsClick)
                    MyPageItem("개인정보 처리방침", onPrivacyPolicyClick)
                }

                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp)
                            .height(8.dp)
                            .background(Color(0xFFFaFaFa))
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    MyPageItem("로그아웃", onLogoutClick)
                }

            }

        }

        Text(
            text = "탈퇴하기",
            color = Color(0xFF888888),
            style = TukPretendardTypography.body14R,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .clickable {onDeleteAccountClick()}
        )
    }

}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
        style = TukPretendardTypography.body14R,
        color = Color(0xFF888888)
    )
}

@Composable
fun MyPageItem(
    title: String,
    onClick: () -> Unit,
    isClickable: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isClickable, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TukPretendardTypography.body16R,
            color = Color(0xFF1F1F1F)
        )

        Image(
            painter = painterResource(R.drawable.icon_arrow),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMyPage(
) {
    MyPageContent("",{}, {}, {}, {}, {}, {}, {},{})
}

