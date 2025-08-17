package com.plottwist.feature.mypage.edit_name

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTextField
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.ui.component.TukScaffold
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EditNameScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditNameViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when (it) {
            EditNameSideEffect.NavigateBack -> {
                onBack()
            }
            EditNameSideEffect.SaveSuccess -> {
                Toast.makeText(
                    context,
                    "이름이 변경되었어요!",
                    Toast.LENGTH_SHORT
                ).show()
                onBack()
            }

            is EditNameSideEffect.ShowToast -> {

            }
        }
    }

    EditNameScreen(
        modifier = modifier,
        name = state.name,
        originalName = state.originalName,
        onBackClick = { viewModel.handleAction(EditNameAction.ClickBack) },
        onSaveClick = { viewModel.handleAction(EditNameAction.ClickSave) }
    )
}

@Composable
private fun EditNameScreen(
    name: TextFieldState,
    originalName: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isNameFocused by remember { mutableStateOf(false) }

    TukScaffold (
        modifier = modifier,
        topBar = {
            EditNameAppBar(onBackClick = onBackClick)
        },
        bottomBar = {
            EditNameSubmitButton(
                isEnabled = name.text.toString() != originalName && name.text.isNotBlank(),
                onClick = onSaveClick
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
                maxLength = 10,
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
fun EditNameAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = "이름 설정",
        onBack = onBackClick
    )
}

@Composable
fun EditNameSubmitButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukSolidButton(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
        text = "저장하기",
        onClick = onClick,
        buttonType = TukSolidButtonType.from(isEnabled)
    )
}

@Preview
@Composable
private fun EditNameScreenPreview() {
    EditNameScreen(
        name = TextFieldState(),
        originalName = "",
        onBackClick = {},
        onSaveClick = {}
    )
}
