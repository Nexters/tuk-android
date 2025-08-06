package com.plottwist.feature.proposal_create.gathering_select

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SelectGatheringViewModel @Inject constructor() : ContainerHost<SelectGatheringState, SelectGatheringSideEffect>, ViewModel() {

    override val container = container<SelectGatheringState, SelectGatheringSideEffect>(SelectGatheringState())

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

    private fun navigateBack() = intent {
        postSideEffect(SelectGatheringSideEffect.NavigateBack)
    }

    private fun selectGathering(gatheringId: Long) = intent {
        reduce { state.copy(selectedGatheringId = gatheringId) }
    }
}
