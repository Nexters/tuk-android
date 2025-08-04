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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.component.TukSolidButton
import com.plottwist.core.designsystem.component.TukSolidButtonType
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.tuk.feature.create_gathering.R

@Composable
fun CreateGatheringNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    onNext: () -> Unit
) {


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


        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "모임명",
                color = Color(0xcccccccc),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))


            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color(0xFFCCCCCC)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(6.dp))
                    .padding(0.dp, top = 8.dp), // 외부 패딩
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 0.dp) // 내부 패딩 제거
                            .fillMaxWidth()
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = "e.g. 오랜만에 한잔해",
                                color = Color(0xFFA0A0A0),
                                style = TukPretendardTypography.body16R
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TukSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = "다음",
            onClick = onNext,
            buttonType = TukSolidButtonType.from(value.isNotBlank())
        )
    }
}

@Composable
@Preview(showBackground = true)
fun previewCreate() {
    CreateGatheringNameInput("", {}) { }
}
