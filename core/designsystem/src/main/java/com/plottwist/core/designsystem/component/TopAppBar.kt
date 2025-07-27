package com.plottwist.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TukTopAppBar(
    title: String? = null,
    modifier: Modifier = Modifier,
    actionButtons: (@Composable RowScope.() -> Unit)? = null,
) {
    title?.let {
        Text(
            text = it,
            modifier = Modifier
                .padding(start = 20.dp),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        )
    }

    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(end = if(actionButtons == null) 20.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        actionButtons?.invoke(this)
    }
}
