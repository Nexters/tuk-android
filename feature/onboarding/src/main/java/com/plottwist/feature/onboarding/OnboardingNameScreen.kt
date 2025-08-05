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
    modifier: Modifier = Modifier,
    viewModel: OnboardingNameViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()


    viewModel.collectSideEffect {

    }

    OnboardingNameScreen(
        modifier = modifier,
        name = state.name,
        onCloseClick = {
            viewModel.handleAction(OnboardingNameAction.ClickClose)
        },
    )
}

@Composable
private fun OnboardingNameScreen(
    name: TextFieldState,
    onCloseClick: () -> Unit,
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
                onClick = { }
            )
        },
        title = stringResource(R.string.onboarding_name_title),
        description = stringResource(R.string.onboarding_name_description),
    ) {
        item {
            TukTextField(
                state = name,
                label = "이름",
                hint = "이름을 입력해 주세요",
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
        actionButtons = {
            TopAppBarCloseButton(onCloseClick)
        }
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
    )
}
