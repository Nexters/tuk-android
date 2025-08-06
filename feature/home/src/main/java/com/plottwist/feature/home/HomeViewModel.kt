package com.plottwist.feature.home

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.CheckLoginStatusUseCase
import com.plottwist.core.domain.gathering.usecase.GetGatheringsUseCase
import com.plottwist.core.weburl.WebUrlConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getGatheringsUseCase: GetGatheringsUseCase,
    private val webViewConfig: WebUrlConfig,
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState()) {
        observeLoginState()
    }

    fun handleAction(action: HomeAction) {
        when (action) {
            HomeAction.ClickMyPage -> {
                handleMyPageClick()
            }

            HomeAction.ClickAddGathering -> {
                handleAddGatheringClick()
            }

            HomeAction.ClickRefreshWhen -> {
                handleRefreshWhenClick()
            }

            HomeAction.ClickRefreshWhere -> {
                handleRefreshWhereClick()
            }

            HomeAction.ClickRefreshWhat -> {
                handleRefreshWhatClick()
            }

            is HomeAction.ClickGathering -> {
                handleGatheringClick(action.gatheringId)
            }

            HomeAction.ClickPropose -> {
                handleProposeClick()
            }

            HomeAction.ClickProposals -> {
                handleProposalsClick()
            }
        }
    }

    private fun observeLoginState() = intent {
        checkLoginStatusUseCase().map { isLoggedIn ->
            if (isLoggedIn) LoginState.LoggedIn else LoginState.LoggedOut
        }.distinctUntilChanged().collectLatest { loginState ->
            when (loginState) {
                LoginState.LoggedIn -> {
                    fetchGatherings(loginState)
                }

                else -> {
                    reduce { state.copy(loginState = loginState) }
                }
            }
        }
    }

    private fun fetchGatherings(loginState: LoginState) = intent {
        val result = getGatheringsUseCase()

        if (result.isSuccess) {
            reduce {
                state.copy(
                    loginState = loginState,
                    gatherings = result.getOrNull() ?: return@reduce state
                )
            }
        } else {
            reduce { state.copy(loginState = loginState) }
        }
    }

    private fun handleMyPageClick() = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToMyPageScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleAddGatheringClick() = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(HomeSideEffect.NavigateToCreateGatheringScreen)
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleGatheringClick(gatheringId: Long) = intent {
        postSideEffect(HomeSideEffect.NavigateToGatheringDetailScreen(gatheringId))
    }

    private fun handleProposeClick() = intent {
        when (state.loginState) {
            LoginState.LoggedIn -> {
                postSideEffect(
                    HomeSideEffect.NavigateToCreateProposalScreen(
                        whereLabel = state.whereLabel,
                        whenLabel = state.whenLabel,
                        whatLabel = state.whatLabel
                    )
                )
            }

            else -> {
                postSideEffect(HomeSideEffect.NavigateToLoginScreen)
            }
        }
    }

    private fun handleRefreshWhenClick() = intent {
        reduce {
            state.copy(
                whenLabel = whenLabels.filterNot {
                    state.whenLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhereClick() = intent {
        reduce {
            state.copy(
                whereLabel = whereLabels.filterNot {
                    state.whereLabel == it
                }.random()
            )
        }
    }

    private fun handleRefreshWhatClick() = intent {
        reduce {
            state.copy(
                whatLabel = whatLabels.filterNot {
                    state.whatLabel == it
                }.random()
            )
        }
    }

    private fun handleProposalsClick() = intent {
        val encodedUrl = URLEncoder.encode(webViewConfig.proposalsUrl,"UTF-8")
        postSideEffect(HomeSideEffect.NavigateToWebViewScreen(encodedUrl))
    }

    companion object {

        // dummy data
        val whereLabels = listOf(
            "제주도 여행가서",
            "한강 잔디밭에서",
            "익선동 골목길 걷다가",
            "경의선 숲길 벤치에 앉아서",
            "비 오는 날 카페 창가 자리에서",
            "야경 좋은 루프탑 바에서",
            "작은 책방 구석에서",
            "무계획으로 도착한 버스터미널에서",
            "감성 캠핑장 모닥불 앞에서",
            "별 보이는 산속에서"
        )

        val whenLabels = listOf(
            "새벽 4시까지",
            "일요일 저녁 감성에 취해",
            "해 질 무렵 노을 보며",
            "비 오는 평일 오후에",
            "퇴근길에 충동적으로",
            "불면의 밤을 함께하며",
            "첫눈 오는 날에 맞춰",
            "달 보며 감성 충전할 때",
        )

        val whatLabels = listOf(
            "전생 이야기 나누기",
            "서로의 흑역사 고백하기",
            "한 사람씩 TMI 발표하기",
            "모두가 울게 되는 영화 보기",
            "MBTI 바꿔서 롤플레잉 대화하기",
            "우주에 대해 진지하게 고민하기",
            "서로의 인생곡 하나씩 틀기",
            "마음 속 진심을 라면에 담아 끓이기",
            "다음 생에 다시 만나기로 약속하기",
            "그냥 아무 말 없이 같이 있기"
        )
    }
}
