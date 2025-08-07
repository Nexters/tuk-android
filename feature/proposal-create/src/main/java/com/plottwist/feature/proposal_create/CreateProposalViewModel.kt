package com.plottwist.feature.proposal_create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.model.GatheringUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateProposalViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ContainerHost<CreateProposalState, CreateProposalSideEffect>, ViewModel() {
    override val container =
        container<CreateProposalState, CreateProposalSideEffect>(
            savedStateHandle.toRoute<Route.CreateProposal>().let { route ->
                CreateProposalState(
                    whereLabel = route.whereLabel,
                    whenLabel = route.whenLabel,
                    whatLabel = route.whatLabel
                )
            }
        )



    fun handleAction(action: CreateProposalAction) {
        when (action) {
            CreateProposalAction.ClickClose -> {
                navigateBack()
            }

            CreateProposalAction.ClickSelectGathering -> {
                handleSelectGatheringClick()
            }

            is CreateProposalAction.SelectedGathering -> {
                handleSelectedGathering(action.gatheringId, action.gatheringName)
            }

            CreateProposalAction.ClickCloseSelectedGathering -> {
                handleCloseSelectedGatheringClick()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(CreateProposalSideEffect.NavigateBack)
    }

    private fun handleSelectGatheringClick() = intent {
        postSideEffect(CreateProposalSideEffect.NavigateToSelectGathering(state.selectedGathering?.id))
    }

    private fun handleSelectedGathering(
        gatheringId: Long,
        gatheringName: String
    ) = intent {
        reduce {
            state.copy(
                selectedGathering = GatheringUiModel(
                    id = gatheringId,
                    name = gatheringName
                )
            )
        }
    }

    private fun handleCloseSelectedGatheringClick() = intent {
        reduce {
            state.copy(selectedGathering = null)
        }
    }
}
