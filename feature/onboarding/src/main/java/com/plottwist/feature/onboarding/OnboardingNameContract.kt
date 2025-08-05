package com.plottwist.feature.onboarding

import androidx.compose.foundation.text.input.TextFieldState

data class OnboardingNameState(
    val name: TextFieldState = TextFieldState(),
)

sealed class OnboardingNameAction {
    data object ClickClose : OnboardingNameAction()
    data object ClickSubmit : OnboardingNameAction()
}

sealed class OnboardingNameSideEffect {
    data object NavigateToHomeScreen : OnboardingNameSideEffect()
}
