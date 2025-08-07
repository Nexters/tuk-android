package com.plottwist.invite_gathering

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class InviteGatheringViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ContainerHost<InviteGatheringState, InviteGatheringSideEffect>, ViewModel() {

    override val container = container<InviteGatheringState, InviteGatheringSideEffect>(InviteGatheringState(
        url = URLDecoder.decode(savedStateHandle.toRoute<Route.InviteGathering>().encodedUrl, "UTF-8")
    ))

    fun handleAction(action: InviteGatheringAction) {
        when (action) {
            InviteGatheringAction.ClickBack -> {
                navigateBack()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(InviteGatheringSideEffect.NavigateBack)
    }
}
