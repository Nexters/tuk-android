package com.plottwist.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun handleAction(action: HomeAction) {
        when (action) {
            HomeAction.ClickMyPage -> {
                handleMyPageClick()
            }
        }
    }

    private fun handleMyPageClick() = intent {
        postSideEffect(HomeSideEffect.NavigateToLoginScreen)
    }
}
