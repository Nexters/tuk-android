package com.example.create_gathering

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateGatheringViewModel @Inject constructor() :
    ContainerHost<CreateGatheringState, CreateGatheringSideEffect>, ViewModel() {

    override val container = container<CreateGatheringState, CreateGatheringSideEffect>(
        CreateGatheringState()
    )

    fun onClickNext() = intent {
        if (state.currentPage < MAX_PAGE) {
            reduce { state.copy(currentPage = state.currentPage + 1) }
        } else {
            postSideEffect(CreateGatheringSideEffect.NavigateToHomeScreen)
        }
    }

    fun onClickPrev() = intent {
        if (state.currentPage > 0) {
            reduce { state.copy(currentPage = state.currentPage - 1) }
        }
    }

    fun onClickSkip() = intent {
        reduce { state.copy(currentPage = MAX_PAGE) }
    }

    fun updateGatheringName(name: String) = intent {
        reduce { state.copy(gatheringName = name) }
    }


    fun updateFrequencyGathering(frequency: String) = intent {
        reduce { state.copy(frequencyGathering = frequency) }
    }

    fun updateTags(tag: String) = intent {
        val newTags = if (state.tags.contains(tag)) {
            state.tags - tag
        } else {
            state.tags + tag
        }
        reduce { state.copy(tags = newTags) }
    }

    companion object {
        private const val MAX_PAGE = 3
    }

}
