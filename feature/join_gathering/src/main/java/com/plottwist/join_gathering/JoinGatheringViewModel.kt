package com.plottwist.join_gathering

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.gathering.usecase.JoinGatheringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class JoinGatheringViewModel @Inject constructor(
    private val joinGatheringUseCase: JoinGatheringUseCase
) :ContainerHost<JoinGatheringState,JoinGatheringSideEffect>, ViewModel(){

    override val container = container<JoinGatheringState,JoinGatheringSideEffect>(JoinGatheringState.Idle)

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
