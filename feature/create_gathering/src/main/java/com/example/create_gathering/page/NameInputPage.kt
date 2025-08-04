package com.example.create_gathering.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.component.TukTextField
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringNameInput(
    state: TextFieldState,
    onNext: () -> Unit,
    onClear: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 17.dp)
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(10.dp))


        StableImage(
            drawableResId = R.drawable.image_name_input_title
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "기존에 쓰던 모임명이 없다면\n센스를 발휘해 보세요",
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
            modifier = Modifier.padding(bottom = 48.dp)
        )
        TukTextField(
            state = state,
            hint = "e.g. 오랜만에 한잔해",
            label = "모임명",
            isFocus = isFocused,
            onFocus = {
                isFocused = it
            },
            onClear = onClear
        )


        Spacer(modifier = Modifier.weight(1f))

        TukSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = "다음",
            onClick = onNext,
            buttonType = TukSolidButtonType.from(state.text.isNotBlank())
        )
    }
}

@Composable
@Preview(showBackground = true)
fun previewCreate() {
    CreateGatheringNameInput(TextFieldState(), { }, { })
}
