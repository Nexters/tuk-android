package com.plottwist.feature.proposal_create.complete

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import com.plottwist.feature.proposal_create.complete.CompleteProposeAction
import com.plottwist.feature.proposal_create.complete.CompleteProposeSideEffect
import com.plottwist.feature.proposal_create.complete.CompleteProposeState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class CompleteProposeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ContainerHost<CompleteProposeState, CompleteProposeSideEffect>, ViewModel() {
    override val container = container<CompleteProposeState, CompleteProposeSideEffect>(
        CompleteProposeState(
            url = URLDecoder.decode(savedStateHandle.toRoute<Route.CompleteProposal>().encodedUrl, "UTF-8")
        )
    )

    fun handleAction(action: CompleteProposeAction) {
        when (action) {
            CompleteProposeAction.ClickClose -> {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() = intent {
        postSideEffect(CompleteProposeSideEffect.NavigateToHome)
    }
}
