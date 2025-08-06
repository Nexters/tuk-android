package com.plottwist.feature.proposal_create.gathering_select

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.GetGatheringsUseCase
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.model.toSelectGatheringUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectGatheringViewModel @Inject constructor(
    private val getGatheringsUseCase: GetGatheringsUseCase,
    savedStateHandle: SavedStateHandle
) : ContainerHost<SelectGatheringState, SelectGatheringSideEffect>, ViewModel() {
    override val container = container<SelectGatheringState, SelectGatheringSideEffect>(
        SelectGatheringState(
            selectedGatheringId = savedStateHandle.toRoute<Route.SelectGathering>().gatheringId
        )
    ) {
        fetchGatherings()
    }

    fun handleAction(action: SelectGatheringAction) {
        when (action) {
            SelectGatheringAction.ClickBack -> {
                navigateBack()
            }
            is SelectGatheringAction.SelectGathering -> {
                selectGathering(action.gatheringId)
            }
        }
    }

    private fun fetchGatherings() = intent {
        val result = getGatheringsUseCase()

        if (result.isSuccess) {
            reduce {
                state.copy(
                    gatherings = result.getOrNull()?.gatheringOverviews?.map {
                        it.toSelectGatheringUiModel()
                    } ?: emptyList()
                )
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(SelectGatheringSideEffect.NavigateBack)
    }

    private fun selectGathering(gatheringId: Long) = intent {
        reduce {
            state.copy(
                gatherings = state.gatherings.map {
                    it.copy(selected = gatheringId == it.id)
                }
            )
        }
    }
}
