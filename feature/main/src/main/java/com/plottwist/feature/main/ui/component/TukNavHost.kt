package com.plottwist.feature.main.ui.component

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.util.Consumer
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.plottwist.core.ui.navigation.Route
import com.plottwist.create_gathering.navigation.createGatheringNavGraph
import com.plottwist.create_gathering.navigation.navigateToCreateGathering
import com.plottwist.feature.gathering_detail.navigation.gatheringDetailAlarmSettingNavGraph
import com.plottwist.feature.gathering_detail.navigation.gatheringDetailNavGraph
import com.plottwist.feature.gathering_detail.navigation.navigateToGatheringDetail
import com.plottwist.feature.gathering_detail.navigation.navigateToGatheringDetailAlarmSetting
import com.plottwist.feature.home.navigation.homeNavGraph
import com.plottwist.feature.home.navigation.navigateToHome
import com.plottwist.feature.login.navigation.loginNavGraph
import com.plottwist.feature.login.navigation.navigateToLogin
import com.plottwist.feature.main.LocalActivity
import com.plottwist.feature.mypage.navigation.editNameNavGraph
import com.plottwist.feature.mypage.navigation.myPageNavGraph
import com.plottwist.feature.mypage.navigation.navigateToEditName
import com.plottwist.feature.mypage.navigation.navigateToMyPage
import com.plottwist.feature.mypage.navigation.navigateToNotificationSetting
import com.plottwist.feature.mypage.navigation.navigateToPolicyWebView
import com.plottwist.feature.mypage.navigation.notificationSettingNavGraph
import com.plottwist.feature.mypage.navigation.policyWebViewNavGraph
import com.plottwist.feature.onboarding.navigation.navigateToOnboardingName
import com.plottwist.feature.onboarding.navigation.onboardingNameNavGraph
import com.plottwist.feature.proposal_create.navigation.completeProposalNavGraph
import com.plottwist.feature.proposal_create.navigation.createGatheringProposalNavGraph
import com.plottwist.feature.proposal_create.navigation.createProposalNavGraph
import com.plottwist.feature.proposal_create.navigation.navigateToCompleteProposal
import com.plottwist.feature.proposal_create.navigation.navigateToCreateGatheringProposal
import com.plottwist.feature.proposal_create.navigation.navigateToCreateProposal
import com.plottwist.feature.proposal_create.navigation.navigateToSelectGathering
import com.plottwist.feature.proposal_create.navigation.selectGatheringNavGraph
import com.plottwist.feature.proposal_detail.navigation.proposalDetailNavGraph
import com.plottwist.feature.webview.navigation.navigateToWebView
import com.plottwist.feature.webview.navigation.webViewNavGraph
import com.plottwist.invite_gathering.navigation.inviteGatheringNavGraph
import com.plottwist.invite_gathering.navigation.navigateToInviteGathering
import com.plottwist.join_gathering.navigation.createJoinGatheringNavGraph

@Composable
fun TukNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val activity = LocalActivity.current

    DisposableEffect(activity, navController) {
        val onNewIntentConsumer = Consumer<Intent> {
            navController.handleDeepLink(it)
        }

        activity.addOnNewIntentListener(onNewIntentConsumer)

        onDispose { activity.removeOnNewIntentListener(onNewIntentConsumer) }
    }


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
                    whatLabel = whatLabel,
                    gatheringId = null,
                    gatheringName = null
                )
            },
            navigateToWebView = { url ->
                navController.navigateToWebView(url)
            },
            navigateToSelectGathering = { whereLabel, whenLabel, whatLabel ->
                navController.navigateToSelectGathering(
                    whereLabel = whereLabel,
                    whenLabel = whenLabel,
                    whatLabel = whatLabel,
                    gatheringId = null
                )
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
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            },
            navigateToNotificationSetting = { navController.navigateToNotificationSetting() },
            navigateToTerms = {
                navController.navigateToPolicyWebView(
                    "서비스 이용약관",
                    "https://www.tuk.kr/service-policy"
                )
            },
            navigateToPrivacyPolicy = {
                navController.navigateToPolicyWebView(
                    "개인정보 처리방침",
                    "https://www.tuk.kr/privacy-policy"
                )
            },
            navigateToEditName = {
                navController.navigateToEditName()
            }
        )
        notificationSettingNavGraph(
            onBack = { navController.popBackStack() }
        )
        policyWebViewNavGraph(
            onClose = { navController.popBackStack() }
        )
        createGatheringNavGraph(
            navigateToHomeScreen = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            },
            onBack = {
                navController.popBackStack()
            }
        )
        gatheringDetailNavGraph(
            onBack = {
                navController.popBackStack()
            },
            navigateToWebViewScreen = { url ->
                navController.navigateToWebView(url)
            },
            navigateToInviteGathering = { url ->
                navController.navigateToInviteGathering(url)
            },
            navigateToCreateGatheringProposal = { id, name ->
                navController.navigateToCreateGatheringProposal(id, name)
            },
            navigateToGatheringDetailAlarmSetting = { id, days ->
                navController.navigateToGatheringDetailAlarmSetting(id, days)
            }
        )
        createProposalNavGraph(
            onBack = {
                navController.popBackStack()
            },
            navigateToSelectGatheringScreen = {
                //        navController.navigateToSelectGathering(it)
            },
            navigateToCompleteProposeScreen = {
                navController.navigateToCompleteProposal(
                    it,
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = false
                        }
                    }
                )
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
        selectGatheringNavGraph(
            onBack = {
                navController.popBackStack()
            },
            navigateToCreateProposalWithGathering = {
                navController.navigateToCreateProposal(
                    gatheringId = it.id,
                    gatheringName = it.name,
                    whenLabel = it.whenLabel,
                    whereLabel = it.whereLabel,
                    whatLabel = it.whatLabel
                )
            }
        )
        inviteGatheringNavGraph(
            onCloseClicked = {
                navController.popBackStack()
            }
        )
        createGatheringProposalNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
        createJoinGatheringNavGraph(
            onBack = {
                navController.popBackStack()
            },
            onNavigateToGatheringDetail = {
                navController.navigateToGatheringDetail(
                    it,
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = false
                        }
                    }
                )
            }
        )
        completeProposalNavGraph(
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            }
        )
        gatheringDetailAlarmSettingNavGraph(
            onBack = {
                navController.popBackStack()
            },
            onNavigateGatheringDetail = { id ->
                navController.navigateToGatheringDetail(
                    gatheringId = id,
                    navOptions = navOptions {
                        popUpTo(Route.Home) {
                            inclusive = false
                        }
                    }
                )
            }
        )

        editNameNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
        proposalDetailNavGraph(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}
