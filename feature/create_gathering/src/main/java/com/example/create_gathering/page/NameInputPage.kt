package com.example.create_gathering.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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


        Text(
            text = "어떤 모임을 만들까요?",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "기존에 쓰던 모임명이 없다면\n센스를 발휘해 보세요",
            modifier = Modifier.padding(bottom = 48.dp)
        )


        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5), // 회색 배경
                    shape = RoundedCornerShape(12.dp) // 둥근 모서리
                )
                .padding(start = 20.dp, end = 20.dp, top = 12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "모임명",
                color = Color(0xcccccccc), // 연한 회색
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))


            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = "e.g. 오랜만에 한잔해",
                        color = Color(0xFFCCCCCC)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color(0xFFCCCCCC),
                    unfocusedTextColor = Color(0xFFCCCCCC)
                ),
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 14.sp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFCCCCCC) // 버튼 배경색
            )
        ) {
            Text("다음")
        }


    }
}

@Composable
@Preview(showBackground = true)
fun previewCreate() {
    CreateGatheringNameInput("sdf", {}) { }
}
