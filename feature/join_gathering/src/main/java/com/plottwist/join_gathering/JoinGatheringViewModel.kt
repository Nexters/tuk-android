package com.plottwist.join_gathering

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.JoinGatheringUseCase
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class JoinGatheringViewModel @Inject constructor(
    private val joinGatheringUseCase: JoinGatheringUseCase,
    savedStateHandle: SavedStateHandle
) :ContainerHost<JoinGatheringState,JoinGatheringSideEffect>, ViewModel(){

    override val container = container<JoinGatheringState,JoinGatheringSideEffect>(JoinGatheringState.Idle) {
        savedStateHandle.toRoute<Route.JoinGathering>().let {
            Log.d("JoinGatheringViewModel", "gatheringId: ${it.gatheringId}")
        }
    }

    fun dispatch(action: JoinGatheringAction) = intent {
        when (action) {
            is JoinGatheringAction.ClickJoin -> {
                reduce { JoinGatheringState.Loading }

                joinGatheringUseCase(action.gatheringId)
                    .collect { result ->
                        result.fold(
                            onSuccess = {
                                postSideEffect(JoinGatheringSideEffect.NavigateToGatheringDetail(it))
                                reduce { JoinGatheringState.Success }
                            },
                            onFailure = {
                                reduce { JoinGatheringState.Error(it.message ?: "에러") }

                            }
                        )
                    }
            } else -> {

            }
        }
    }
}
