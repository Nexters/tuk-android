package com.plottwist.feature.mypage.edit_name

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.auth.usecase.UpdateMemberNameUseCase
import com.plottwist.core.domain.onboarding.usecase.GetMemberInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EditNameViewModel @Inject constructor(
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val updateMemberNameUseCase : UpdateMemberNameUseCase
) : ContainerHost<EditNameState, EditNameSideEffect>, ViewModel() {
    override val container = container<EditNameState, EditNameSideEffect>(EditNameState()) {
        getMemberInfo()
    }

    private fun getMemberInfo() = intent {
        getMemberInfoUseCase().onSuccess { result ->
            reduce {
                state.copy(
                    name = TextFieldState(result.name),
                    originalName = result.name
                )
            }
        }.onFailure {

        }
    }

    fun handleAction(action: EditNameAction) {
        when (action) {
            EditNameAction.ClickBack -> {
                navigateBack()
            }
            EditNameAction.ClickSave -> {
                saveName()
            }
        }
    }

    private fun navigateBack() = intent {
        postSideEffect(EditNameSideEffect.NavigateBack)
    }


    private fun saveName() = intent {
        updateMemberNameUseCase(state.name.text.toString())
            .onSuccess {
                postSideEffect(EditNameSideEffect.SaveSuccess)
            }.onFailure {

            }
    }
}
