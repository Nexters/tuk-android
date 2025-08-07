package com.plottwist.feature.main.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.create_gathering.navigation.createGatheringNavGraph
import com.example.create_gathering.navigation.navigateToCreateGathering
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.gathering_detail.navigation.gatheringDetailNavGraph
import com.plottwist.feature.gathering_detail.navigation.navigateToGatheringDetail
import com.plottwist.feature.home.navigation.homeNavGraph
import com.plottwist.feature.login.navigation.loginNavGraph
import com.plottwist.feature.login.navigation.navigateToLogin
import com.plottwist.feature.mypage.navigation.myPageNavGraph
import com.plottwist.feature.mypage.navigation.navigateToMyPage
import com.plottwist.feature.mypage.navigation.navigateToNotificationSetting
import com.plottwist.feature.mypage.navigation.navigateToPolicyWebView
import com.plottwist.feature.mypage.navigation.notificationSettingNavGraph
import com.plottwist.feature.mypage.navigation.policyWebViewNavGraph
import com.plottwist.feature.onboarding.navigation.navigateToOnboardingName
import com.plottwist.feature.onboarding.navigation.onboardingNameNavGraph
import com.plottwist.feature.proposal_create.navigation.createProposalNavGraph
import com.plottwist.feature.proposal_create.navigation.navigateToCreateProposal
import com.plottwist.feature.webview.navigation.navigateToWebView
import com.plottwist.feature.webview.navigation.webViewNavGraph

@Composable
fun TukNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home
    ) {
        homeNavGraph(
            navigateToLoginScreen = {
                navController.navigateToLogin()
            },
            navigateToMyPageScreen = {
                navController.navigateToMyPage()
            },
            navigateToCreateGathering = {
                navController.navigateToCreateGathering()
            },
            navigateToGatheringDetail = { id ->
                navController.navigateToGatheringDetail(gatheringId = id)
            },
            navigateToCreateProposal = { whereLabel, whenLabel, whatLabel ->
                navController.navigateToCreateProposal(
                    whereLabel = whereLabel,
                    whenLabel = whenLabel,
                    whatLabel = whatLabel
                )
            },
            navigateToWebView = { url ->
                navController.navigateToWebView(url)
            }
        )
        loginNavGraph(
            onBack = {
                navController.popBackStack()
            },
            navigateToOnboarding = {
                navController.navigateToOnboardingName()
            }
        )
        myPageNavGraph(
            onBack = { navController.popBackStack() },
            navigateToNotificationSetting = { navController.navigateToNotificationSetting() },
            navigateToTerms = { navController.navigateToPolicyWebView("서비스 이용약관","https://www.tuk.kr/service-policy")},
            navigateToPrivacyPolicy = { navController.navigateToPolicyWebView("개인정보 처리방침","https://www.tuk.kr/privacy-policy")}
        )
        notificationSettingNavGraph(
            onBack = { navController.popBackStack() }
        )
        policyWebViewNavGraph(
            onClose = { navController.popBackStack() }
        )
        createGatheringNavGraph(
            onSubmit = {
                navController.popBackStack(Route.Home, inclusive = false)
            }
        )
        gatheringDetailNavGraph(
            onBack = {
                navController.popBackStack()
            },
            navigateToWebViewScreen = { url ->
                navController.navigateToWebView(url)
            }
        )
        createProposalNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
        onboardingNameNavGraph(
            onBack = {

            },
            navigateToHomeScreen = {
                navController.popBackStack(Route.Home, inclusive = false)
            }
        )
        webViewNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}
