package com.plottwist.feature.onboarding

import androidx.lifecycle.ViewModel
import com.plottwist.core.domain.onboarding.usecase.UpdateOnboardingInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class OnboardingNameViewModel @Inject constructor(
    private val updateOnboardingInfoUseCase: UpdateOnboardingInfoUseCase
) : ContainerHost<OnboardingNameState, OnboardingNameSideEffect>, ViewModel() {

    override val container = container<OnboardingNameState, OnboardingNameSideEffect>(OnboardingNameState())

    fun handleAction(action: OnboardingNameAction) {
        when (action) {
            OnboardingNameAction.ClickClose -> {

            }

            OnboardingNameAction.ClickSubmit -> {
                handleSubmitClick()
            }
        }
    }

    private fun handleSubmitClick() = intent {
        val result = updateOnboardingInfoUseCase(state.name.text.toString())

        if (result.isSuccess) {
            postSideEffect(OnboardingNameSideEffect.NavigateToBack)
        }
    }
}
