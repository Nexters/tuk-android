package com.plottwist.feature.proposal_create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateProposalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
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
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(CreateProposalSideEffect.NavigateBack)
    }
}
