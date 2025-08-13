package com.plottwist.feature.gathering_detail.alarm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.plottwist.core.domain.gathering.usecase.UpdateGatheringUseCase
import com.plottwist.core.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GatheringDetailAlarmSettingViewModel @Inject constructor(
    private val updateGatheringUseCase: UpdateGatheringUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(),
    ContainerHost<GatheringDetailAlarmSettingState, GatheringDetailAlarmSettingSideEffect> {
    override val container =
        container<GatheringDetailAlarmSettingState, GatheringDetailAlarmSettingSideEffect>(
            savedStateHandle.toRoute<Route.GatheringAlarmSetting>().let {
                GatheringDetailAlarmSettingState(
                    selectedIntervalDays = it.selectedIntervalDays.toInt(),
                    gatheringId = it.gatheringId
                )
            }
        )

    fun handleAction(action: GatheringDetailAlarmSettingAction) {
        when (action) {
            GatheringDetailAlarmSettingAction.ClickBack -> {
                navigateBack()
            }

            is GatheringDetailAlarmSettingAction.ClickOption -> {
                handleOptionClick(action.days)
            }

            GatheringDetailAlarmSettingAction.ClickSave -> {
                handleSaveClick()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(GatheringDetailAlarmSettingSideEffect.NavigateBack)
    }

    private fun handleOptionClick(days: Int) = intent {
        reduce {
            state.copy(
                selectedIntervalDays = days
            )
        }
    }

    private fun handleSaveClick() = intent {
        updateGatheringUseCase(
            gatheringId = state.gatheringId,
            intervalDays = state.selectedIntervalDays
        ).onSuccess {
            postSideEffect(
                GatheringDetailAlarmSettingSideEffect.NavigateGatheringDetail(
                    state.gatheringId
                )
            )
        }.onFailure {

        }
    }
}
