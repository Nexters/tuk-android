package com.plottwist.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray700
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography

@Composable
fun RadioButtonItem(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hasDivider: Boolean = false
) {
    Column (
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = null,
                    indication = null
                ) {
                    onClick()
                }
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = TukPretendardTypography.body14M,
                    color = Gray900
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = TukPretendardTypography.body12L,
                    color = Gray700
                )
            }

            RadioButton(
                selected = selected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Black,
                    unselectedColor = Color(0xFFCCCCCC)
                )
            )
        }

        if (hasDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(0.dp),
                thickness = 1.dp,
                color = Color(0xFFEEEEEE)
            )
        }
    }
}
