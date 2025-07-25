package com.example.create_gathering

import androidx.lifecycle.ViewModel
import com.example.create_gathering.model.GatheringHashTag
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateGatheringViewModel @Inject constructor():ContainerHost<CreateGatheringState, CreateGatheringSideEffect>,ViewModel() {

    override val container = container<CreateGatheringState, CreateGatheringSideEffect>(
        CreateGatheringState()
    )

    fun onClickNext() = intent {
        if (state.currentPage <MAX_PAGE) {
            reduce { state.copy(currentPage = state.currentPage +1) }
        } else {
            postSideEffect(CreateGatheringSideEffect.NavigateToHomeScreen)
        }
    }

    fun onClickPrev() = intent {
        if (state.currentPage >0) {
            reduce { state.copy(currentPage = state.currentPage -1) }
        }
    }

    fun onClickSkip() = intent {
        reduce { state.copy(currentPage = MAX_PAGE) }
    }

    fun updateGatheringName(name: String) = intent {
        reduce { state.copy(gatheringName = name) }
    }

    fun updateLastGatheringType(type:String) = intent {
        reduce { state.copy(lastGathering = type) }
    }

    fun updateFrequencyGathering(frequency: String) = intent {
        reduce { state.copy(frequencyGathering = frequency) }
    }

    fun updateDescription(desc: String) = intent {
        reduce { state.copy(description = desc) }
    }

    fun toggleTag(tag: GatheringHashTag) = intent {
        val newTags = if (state.tags.any { it.id == tag.id }) {
            state.tags.filterNot { it.id == tag.id }
        } else {
            state.tags + tag
        }
        reduce { state.copy(tags = newTags) }
    }

    fun onAddTag(newTag: GatheringHashTag) = intent {
        if (state.tags.none { it.id == newTag.id }) {
            reduce { state.copy(tags = state.tags + newTag) }
        }
    }

     companion object {
         private const val MAX_PAGE = 3
     }

}