package com.plottwist.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    paddingValues: PaddingValues = PaddingValues(),
    content : @Composable BoxScope.() -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(shape)
            .border(
                width = 1.dp,
                color = Color(0xFF000000).copy(0.05f),
                shape = shape
            )
            .background(
                color = Color(0xFFF0F1F3),
                shape = shape
            )
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box (
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            content()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.post_card_bottom_description_start),
                style = TukSerifTypography.body14R.copy(
                    fontSize = 12.sp
                ),
                color = Color(0xFFCCCCCC)
            )

            Text(
                text = stringResource(R.string.post_card_bottom_description_end),
                style = TukSerifTypography.body14R.copy(
                    fontSize = 12.sp
                ),
                color = Color(0xFFCCCCCC)
            )
        }
    }
}
