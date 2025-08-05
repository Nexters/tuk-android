package com.example.create_gathering

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import com.example.create_gathering.model.toPresentation
import com.plottwist.core.domain.gathering.model.CreateGatheringModel
import com.plottwist.core.domain.gathering.usecase.CreateGatheringUseCase
import com.plottwist.core.domain.gathering.usecase.GetGatheringTagsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CreateGatheringViewModel @Inject constructor(
    private val getGatheringTagsUseCase: GetGatheringTagsUseCase,
    private val createGatheringUseCase: CreateGatheringUseCase
) : ContainerHost<CreateGatheringState, CreateGatheringSideEffect>, ViewModel() {

    override val container = container<CreateGatheringState, CreateGatheringSideEffect>(
        CreateGatheringState()
    )
    init {
        fetchTags()
    }
    private fun fetchTags() = intent {
        getGatheringTagsUseCase().collect { result ->
            result
                .onSuccess { categories ->
                    val allTags = categories.flatMap { it.tags }
                    val uiTags = allTags.map { it.toPresentation() }
                    reduce { state.copy(tags = uiTags) }
                }
                .onFailure {
                }
        }
    }

    fun onAction(action: CreateGatheringAction) = intent {
        when (action) {
            is CreateGatheringAction.ClickNext -> {
                if (state.currentPage < MAX_PAGE) {
                    reduce { state.copy(currentPage = state.currentPage + 1) }
                } else {
                    postSideEffect(CreateGatheringSideEffect.NavigateToHomeScreen)
                }
            }

            is CreateGatheringAction.ClickPrev -> {
                if (state.currentPage > 0) {
                    reduce { state.copy(currentPage = state.currentPage - 1) }
                }
            }

            is CreateGatheringAction.ClickSkip -> {
                reduce { state.copy(currentPage = MAX_PAGE) }
            }

            is CreateGatheringAction.ToggleTag -> {
                val updated = if (state.tags.contains(action.tag)) {
                    state.tags - action.tag
                } else {
                    state.tags + action.tag
                }
                reduce { state.copy(tags = updated) }
            }

            is CreateGatheringAction.UpdateIntervalDays -> {
                reduce { state.copy(intervalDays = action.intervalDays) }
            }

            is CreateGatheringAction.SubmitGathering -> {
                createGathering()
            }

            CreateGatheringAction.ClearGatheringName -> {
                state.gatheringName.clearText()
            }
        }
    }

    private fun createGathering() = intent {
        val model = CreateGatheringModel(
            name = state.gatheringName.text.toString(),
            intervalDays = state.intervalDays,
            tagIds = state.tags.map { it.id }
        )

        createGatheringUseCase(model)
            .onSuccess {
                postSideEffect(CreateGatheringSideEffect.NavigateToHomeScreen)
            }
            .onFailure {
            }
    }

    companion object {
        private const val MAX_PAGE = 3
    }

}
