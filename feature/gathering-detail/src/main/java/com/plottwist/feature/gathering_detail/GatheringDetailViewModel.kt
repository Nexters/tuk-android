package com.plottwist.feature.gathering_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.GetGatheringDetailUseCase
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GatheringDetailViewModel @Inject constructor(
    private val getGatheringDetailUseCase: GetGatheringDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ContainerHost<GatheringDetailState, GatheringDetailSideEffect>, ViewModel() {
    override val container = container<GatheringDetailState, GatheringDetailSideEffect>(
        GatheringDetailState()
    ) {
        fetchGatheringDetail()
    }
    private val gatheringId = savedStateHandle.toRoute<Route.GatheringDetail>().gatheringId

    private fun fetchGatheringDetail() = intent {
        val result = getGatheringDetailUseCase(gatheringId)

        if(result.isSuccess) {
            reduce {
                state.copy(
                    gatheringDetail = result.getOrNull() ?: return@reduce state
                )
            }
        }
    }
}
