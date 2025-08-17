package com.plottwist.feature.mypage.edit_name

import androidx.compose.foundation.text.input.TextFieldState

data class EditNameState(
    val name: TextFieldState = TextFieldState(),
    val originalName: String = "",
    val isLoading: Boolean = false
)

sealed class EditNameAction {
    data object ClickBack : EditNameAction()
    data object ClickSave : EditNameAction()
}

sealed class EditNameSideEffect {
    data object NavigateBack : EditNameSideEffect()
    data object SaveSuccess : EditNameSideEffect()
    data class ShowToast(val message: String) : EditNameSideEffect()
}
