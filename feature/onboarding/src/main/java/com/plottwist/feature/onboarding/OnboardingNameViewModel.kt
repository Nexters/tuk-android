package com.plottwist.feature.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class OnboardingNameViewModel @Inject constructor() : ContainerHost<OnboardingNameState, OnboardingNameSideEffect>, ViewModel() {

    override val container = container<OnboardingNameState, OnboardingNameSideEffect>(OnboardingNameState())

    fun handleAction(action: OnboardingNameAction) {
        when (action) {
            OnboardingNameAction.ClickClose -> {

            }
        }
    }

}
