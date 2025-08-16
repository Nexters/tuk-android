package com.plottwist.feature.mypage.edit_name

data class EditNameState(
    val name: String = "",
    val isLoading: Boolean = false
)

sealed class EditNameAction {
    data object ClickBack : EditNameAction()
    data class OnNameChanged(val name: String) : EditNameAction()
    data object ClickSave : EditNameAction()
}

sealed class EditNameSideEffect {
    data object NavigateBack : EditNameSideEffect()
    data object SaveSuccess : EditNameSideEffect()
    data class ShowToast(val message: String) : EditNameSideEffect()
}
