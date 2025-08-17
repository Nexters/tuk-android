package com.plottwist.create_gathering.page

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTextField
import com.plottwist.core.ui.component.TukScaffold

@Composable
fun CreateGatheringNameInput(
    state: TextFieldState,
    onNext: () -> Unit,
    onClear: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TukScaffold(
        title = "어떤 모임을\n만들까요?",
        description = "기존에 쓰던 모임명이 없다면\n센스를 발휘해 보세요",
        bottomBar = {
            TukSolidButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                text = "다음",
                onClick = {
                    keyboardController?.hide()
                    onNext()
                },
                buttonType = TukSolidButtonType.from(state.text.isNotBlank())
            )
        }
    ) {
        item {
            TukTextField(
                state = state,
                hint = "e.g. 오랜만에 한잔해",
                label = "모임명",
                isFocus = isFocused,
                onFocus = {
                    isFocused = it
                },
                onClear = onClear,
                maxLength = 10
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun previewCreate() {
    CreateGatheringNameInput(TextFieldState(), { }, { })
}
