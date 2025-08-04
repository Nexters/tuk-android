package com.plottwist.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography

@Composable
fun TukRoundSolidButton(
    text: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(100)
            )
            .background(
                color = containerColor,
                shape = RoundedCornerShape(100)
            )
            .clickable {
                onClick()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 10.dp
            )
           ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = text,
            style = TukPretendardTypography.body14M,
            color = contentColor
        )

        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}
