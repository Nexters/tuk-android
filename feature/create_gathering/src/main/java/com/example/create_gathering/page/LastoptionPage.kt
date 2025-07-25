package com.example.create_gathering.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.component.component.TukButtonType
import com.plottwist.core.designsystem.component.component.TukTextButton

@Composable
fun CreateGatheringSelectOption(selected:String, onSelect:(String)->Unit) {
    val options = listOf("asdfas","Sdf","sdf","asdf")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Text(
            text = "우리들의 마지막 만남은",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        options.forEach{option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(option) }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == option,
                    onClick = {onSelect(option)}
                )

                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            TukTextButton(
                text = "이전",
                onClick = {  },
                buttonType = TukButtonType.SECONDARY,
                modifier = Modifier.weight(1f)
            )

            TukTextButton(
                text = "다음",
                onClick = {  },
                buttonType = TukButtonType.ACTIVE,
                modifier = Modifier.weight(1f)
            )
        }
    }
}