package com.plottwist.feature.proposal_create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateProposalViewModel @Inject constructor(

) : ContainerHost<CreateProposalState, CreateProposalSideEffect>, ViewModel() {
    override val container =
        container<CreateProposalState, CreateProposalSideEffect>(CreateProposalState())

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
