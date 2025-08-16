package com.plottwist.feature.mypage.edit_name

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor() : ContainerHost<EditNameState, EditNameSideEffect>, ViewModel() {

    override val container = container<EditNameState, EditNameSideEffect>(EditNameState())

    fun handleAction(action: EditNameAction) {
        when (action) {
            EditNameAction.ClickBack -> {
                navigateBack()
            }
            is EditNameAction.OnNameChanged -> {
                onNameChanged(action.name)
            }
            EditNameAction.ClickSave -> {
                saveName()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(EditNameSideEffect.NavigateBack)
    }

    private fun onNameChanged(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    private fun saveName() = intent {
        // TODO: Implement actual save logic
        postSideEffect(EditNameSideEffect.SaveSuccess)
        postSideEffect(EditNameSideEffect.ShowToast("이름이 저장되었습니다."))
    }
}
