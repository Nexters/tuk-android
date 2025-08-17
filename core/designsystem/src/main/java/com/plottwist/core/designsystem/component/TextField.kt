package com.plottwist.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray100
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray500
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray700
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography

@Composable
fun TukTextField(
    state: TextFieldState,
    hint: String,
    label: String,
    isFocus: Boolean,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = 0,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onFocus: (Boolean) -> Unit = {}
) {
    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState -> onFocus(focusState.isFocused) },
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = TukPretendardTypography.body16R.copy(
            color = Gray900
        ),
        inputTransformation = InputTransformation.Companion.maxLength(maxLength),
        decorator = { innerTextField ->
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (isFocus) Gray000 else Gray100,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .then(
                            if (isFocus) Modifier.border(
                                width = 1.dp,
                                color = Gray900,
                                shape = RoundedCornerShape(15.dp)
                            ) else Modifier
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(20.dp)
                    ) {
                        if (label.isNotBlank()) {
                            Text(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = label,
                                style = TukPretendardTypography.body12M,
                                color = Gray500
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            if (state.text.isEmpty()) {
                                Text(
                                    text = hint,
                                    color = Gray700,
                                    style = TukPretendardTypography.body16R
                                )
                            }
                            innerTextField()
                        }
                    }
                    if (state.text.isNotEmpty() && isFocus) {
                        ClearButton(
                            modifier = Modifier.padding(end = 10.dp),
                            onClick = onClear
                        )
                    }
                }
                if (maxLength != 0) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp,end = 10.dp),
                        text = "${state.text.length}/${maxLength}",
                        style = TukPretendardTypography.body12R,
                        color = Gray500,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    )
}

@Composable
fun ClearButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delete_circle),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun TukTextFieldPreview() {
    TukTextField(
        state = TextFieldState(),
        hint = "",
        label = "모임명",
        isFocus = false,
        onClear = {}
    )
}

@Preview
@Composable
private fun TukTextFieldPreview1() {
    TukTextField(
        state = TextFieldState(),
        hint = "가이드 텍스트 노출 영역",
        label = "모임명",
        isFocus = false,
        onClear = {}
    )
}

@Preview
@Composable
private fun TukTextFieldPreview2() {
    TukTextField(
        state = TextFieldState("안녕"),
        hint = "가이드 텍스트 노출 영역",
        label = "모임명",
        isFocus = true,
        onClear = {}
    )
}
