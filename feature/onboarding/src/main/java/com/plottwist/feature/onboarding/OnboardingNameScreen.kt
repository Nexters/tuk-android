package com.plottwist.feature.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTextField
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.ui.component.TopAppBarCloseButton
import com.plottwist.core.ui.component.TukScaffold
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun OnboardingNameScreen(
    onBack: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingNameViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current


    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            OnboardingNameSideEffect.NavigateToHomeScreen -> {
                keyboardController?.hide()
                navigateToHomeScreen()
            }
        }

    }

    OnboardingNameScreen(
        modifier = modifier,
        name = state.name,
        onCloseClick = {
            viewModel.handleAction(OnboardingNameAction.ClickClose)
        },
        onSubmitClick = {
            viewModel.handleAction(OnboardingNameAction.ClickSubmit)
        }
    )
}

@Composable
private fun OnboardingNameScreen(
    name: TextFieldState,
    onCloseClick: () -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isNameFocused by remember { mutableStateOf(false) }

    TukScaffold (
        modifier = modifier,
        topBar = {
            OnboardingNameAppBar(onCloseClick = onCloseClick)
        },
        bottomBar = {
            OnboardingSubmitButton(
                isEnabled = name.text.isNotBlank(),
                onClick = onSubmitClick
            )
        },
        title = stringResource(R.string.onboarding_name_title),
        description = stringResource(R.string.onboarding_name_description),
    ) {
        item {
            TukTextField(
                state = name,
                label = stringResource(R.string.onboarding_name_text_field_label),
                hint = stringResource(R.string.onboarding_name_text_field_hint),
                isFocus = isNameFocused,
                onFocus = {
                    isNameFocused = it
                },
                onClear = {
                    name.clearText()
                }
            )
        }

    }

}

@Composable
fun OnboardingNameAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = { }
    )
}

@Composable
fun OnboardingSubmitButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukSolidButton(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
        text = "시작하기",
        onClick = onClick,
        buttonType = TukSolidButtonType.from(isEnabled)
    )
}

@Preview
@Composable
private fun OnboardingNameScreenPreview() {
    OnboardingNameScreen(
        name = TextFieldState(),
        onCloseClick = {},
        onSubmitClick = {}
    )
}
