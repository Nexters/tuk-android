package com.plottwist.join_gathering

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.GetGatheringNameUseCase
import com.plottwist.core.domain.gathering.usecase.JoinGatheringUseCase
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class JoinGatheringViewModel @Inject constructor(
    private val joinGatheringUseCase: JoinGatheringUseCase,
    private val getGatheringNameUseCase : GetGatheringNameUseCase,
    savedStateHandle: SavedStateHandle
) :ContainerHost<JoinGatheringState,JoinGatheringSideEffect>, ViewModel(){

    override val container = container<JoinGatheringState,JoinGatheringSideEffect>(
        savedStateHandle.toRoute<Route.JoinGathering>().gatheringId?.let {
            JoinGatheringState(it)
        } ?: JoinGatheringState()
    ) {
        getGatheringName()
    }

    private fun getGatheringName() = intent {
        getGatheringNameUseCase(state.gatheringId)
            .collect { result ->
                result.fold(
                    onSuccess = {
                        reduce {
                            state.copy(
                                gatheringName = it
                            )
                        }
                    },
                    onFailure = {
                    }
                )
            }
    }

    fun handleAction(action: JoinGatheringAction) = intent {
        when (action) {
            is JoinGatheringAction.ClickJoin -> {
                joinGatheringUseCase(state.gatheringId)
                    .collect { result ->
                        result.fold(
                            onSuccess = {
                                postSideEffect(JoinGatheringSideEffect.NavigateToGatheringDetail(state.gatheringId))
                            },
                            onFailure = {
                                postSideEffect(JoinGatheringSideEffect.ShowSnackbar("모임에 입장할 수 없습니다."))
                            }
                        )
                    }
            } else -> {

            }
        }
    }
}
