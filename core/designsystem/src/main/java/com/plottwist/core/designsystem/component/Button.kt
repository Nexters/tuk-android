package com.plottwist.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.foundation.TukColorTokens.Grey000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Grey200
import com.plottwist.core.designsystem.foundation.TukColorTokens.Grey500
import com.plottwist.core.designsystem.foundation.TukColorTokens.Grey900
import com.plottwist.core.designsystem.foundation.TukPrimitivesColor
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography

enum class TukSolidButtonType(
    val containerColor: Color,
    val contentColor: Color
) {
    ACTIVE(containerColor = TukPrimitivesColor.Primary500, contentColor = Grey000),
    DISABLED(containerColor = Grey200, contentColor = Grey000 )
}


@Composable
fun TukSolidButton(
    text:String,
    modifier: Modifier = Modifier,
    onClick: () ->Unit,
    buttonType: TukSolidButtonType = TukSolidButtonType.ACTIVE,
    textStyle: TextStyle = TukPretendardTypography.body16M,
) {
    TukButton(
        modifier = modifier,
        containerColor = TukSolidButtonType.ACTIVE.containerColor,
        disabledContainerColor = TukSolidButtonType.DISABLED.containerColor,
        contentColor = TukSolidButtonType.ACTIVE.contentColor,
        disabledContentColor = TukSolidButtonType.DISABLED.contentColor,
        enabled = buttonType == TukSolidButtonType.ACTIVE,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}

@Composable
fun TukOutlinedButton(
    text:String,
    modifier: Modifier = Modifier,
    onClick: () ->Unit,
    textStyle: TextStyle = TukPretendardTypography.body16M,
) {
    TukButton(
        modifier = modifier,
        containerColor = Grey000,
        disabledContainerColor = Grey000,
        contentColor = Grey900,
        disabledContentColor = Grey900,
        border = BorderStroke(1.dp, Grey500),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}

@Composable
private fun TukButton(
    containerColor: Color,
    disabledContainerColor: Color,
    contentColor: Color,
    disabledContentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape:Shape = RoundedCornerShape(15.dp),
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier.height(52.dp),
        enabled = enabled,
        shape = shape,
        border = border,
        colors = ButtonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        contentPadding = contentPadding,
        onClick = onClick
    ) {
        content()
    }
}

@Preview
@Composable
private fun TukSolidButtonPreview1() {
    TukSolidButton(
        modifier = Modifier.fillMaxWidth(),
        text = "테스트",
        onClick = {}
    )
}

@Preview
@Composable
private fun TukSolidButtonPreview2() {
    TukSolidButton(
        modifier = Modifier.fillMaxWidth(),
        text = "테스트",
        buttonType = TukSolidButtonType.DISABLED,
        onClick = {}
    )
}

@Preview
@Composable
private fun TukOutlinedButtonPreview() {
    TukOutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        text = "테스트",
        onClick = {}
    )
}
