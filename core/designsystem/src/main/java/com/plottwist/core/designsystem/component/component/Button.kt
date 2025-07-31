package com.plottwist.core.designsystem.component.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class TukButtonType(
    val containerColor: Color,
    val contentColor: Color = Color.White
) {
    ACTIVE(containerColor = Color(0xFF4B0000)),
    SECONDARY(containerColor = Color.White, contentColor = Color.Black),
    DISABLED(containerColor = Color(0xFFE0E0E0), contentColor = Color(0xFF9E9E9E))
}


@Composable
fun TukTextButton(
    text:String,
    modifier: Modifier = Modifier,
    onClick: () ->Unit,
    buttonType: TukButtonType = TukButtonType.ACTIVE,
    textColor: Color = Color(0xFFFFFFFF),
    textStyle: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
) {

    TukButton(
        modifier = modifier,
        buttonType = buttonType,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor
        )
    }

}

@Composable
fun TukButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: TukButtonType=TukButtonType.ACTIVE,
    shape:Shape = RoundedCornerShape(30.dp),
    contentPadding: PaddingValues = PaddingValues(vertical = 18.dp, horizontal =30.dp ),
    content: @Composable () -> Unit
) {

    Button(
        modifier = modifier,
        enabled = buttonType != TukButtonType.DISABLED,
        shape = shape,
        colors = ButtonColors(
            containerColor = buttonType.containerColor,
            disabledContainerColor = TukButtonType.DISABLED.containerColor,
            contentColor = buttonType.contentColor,
            disabledContentColor = TukButtonType.DISABLED.contentColor
        ),
        contentPadding = contentPadding,
        onClick = onClick
    ) {
        content()
    }
}

