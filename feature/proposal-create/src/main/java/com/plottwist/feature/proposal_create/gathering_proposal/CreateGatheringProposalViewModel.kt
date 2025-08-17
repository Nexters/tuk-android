package com.plottwist.feature.proposal_create.gathering_proposal

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.GetPurposesUseCase
import com.plottwist.core.domain.model.Purposes
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.CreateProposalState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateGatheringProposalViewModel @Inject constructor(
    private val getPurposesUseCase: GetPurposesUseCase,
    savedStateHandle: SavedStateHandle
) : ContainerHost<CreateGatheringProposalState, CreateGatheringProposalSideEffect>, ViewModel() {
    override val container = container<CreateGatheringProposalState, CreateGatheringProposalSideEffect>(
        savedStateHandle.toRoute<Route.CreateGatheringProposal>().let { route ->
            CreateGatheringProposalState(
                gatheringName = route.gatheringName,
                gatheringId = route.gatheringId
            )
        }) {
        getPurposes()
    }

    private fun getPurposes() = intent {
        val result = getPurposesUseCase().getOrNull() ?: Purposes()

        reduce {
            state.copy(
                whenTags = result.whenTags,
                whereTags = result.whereTags,
                whatTags = result.whatTags,
                whatLabel = TextFieldState(result.whatTags.firstOrNull() ?: ""),
                whereLabel = TextFieldState(result.whereTags.firstOrNull() ?: ""),
                whenLabel = TextFieldState(result.whenTags.firstOrNull() ?: "")
            )
        }

    }

    fun handleAction(action: CreateGatheringProposalAction) {
        when (action) {
            CreateGatheringProposalAction.ClickBack -> {
                navigateBack()
            }

            CreateGatheringProposalAction.ClickWhatRefresh -> {
                handleWhatRefresh()
            }

            CreateGatheringProposalAction.ClickWhenRefresh -> {
                handleWhenRefresh()
            }

            CreateGatheringProposalAction.ClickWhereRefresh -> {
                handleWhereRefresh()
            }

            CreateGatheringProposalAction.ClickNext -> {
                handleNext()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(CreateGatheringProposalSideEffect.NavigateBack)
    }

    private fun handleWhatRefresh() = intent {
        reduce {
            state.copy(
                whatLabel = TextFieldState(
                    state.whatTags.filterNot {
                        state.whatLabel.text == it
                    }.random()
                )
            )
        }
    }

    private fun handleWhenRefresh() = intent {
        reduce {
            state.copy(
                whenLabel = TextFieldState(
                    state.whenTags.filterNot {
                        state.whenLabel.text == it
                    }.random()
                )
            )
        }
    }

    private fun handleWhereRefresh() = intent {
        reduce {
            state.copy(
                whereLabel = TextFieldState(
                    state.whereTags.filterNot {
                        state.whereLabel.text == it
                    }.random()
                )
            )
        }
    }

    private fun handleNext() = intent {
        reduce {
            state.copy(
                isReady = true
            )
        }
    }
}
