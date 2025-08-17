package com.plottwist.feature.home.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukRoundSolidButton
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.feature.home.LoginState

@Composable
fun HomeContent(
    gatherings: Gatherings,
    onAddGatheringClick: () -> Unit,
    onGatheringClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        GatheringsCard(
            gatherings = gatherings,
            onCreateGatheringClick = onAddGatheringClick,
            onGatheringClick = onGatheringClick
        )
    }
}

@Composable
fun HomeCreateGatheringPreview(
    onAddGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_bottom_create_gathering_description),
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
            textAlign = TextAlign.Center
        )
        TukRoundSolidButton(
            text = stringResource(R.string.home_bottom_create_gathering_button_text),
            containerColor = Color(0xFFFF3838),
            contentColor = Color(0xFFFFFFFF),
            onClick = onAddGatheringClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle),
                contentDescription = stringResource(R.string.home_bottom_create_gathering_button_text),
                tint = Color(0xFFFFFFFF)
            )
        }
    }
}


fun Modifier.outerDropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(alpha = 0.2f),
    blur: Dp = 10.dp,
    offsetX: Dp = 2.dp,
    offsetY: Dp = 5.dp,
    spread: Dp = 1.dp,
): Modifier = drawBehind {
    val spreadPx = spread.toPx()
    val blurPx = blur.toPx()
    val dx = offsetX.toPx()
    val dy = offsetY.toPx()

    val innerOutline = shape.createOutline(size, layoutDirection, this)
    val innerPath = when (innerOutline) {
        is Outline.Generic -> innerOutline.path
        is Outline.Rounded -> Path().apply { addRoundRect(innerOutline.roundRect) }
        is Outline.Rectangle -> Path().apply { addRect(innerOutline.rect) }
    }

    val outerSize = Size(size.width + spreadPx, size.height + spreadPx)
    val outerOutline = shape.createOutline(outerSize, layoutDirection, this)
    val outerPath = when (outerOutline) {
        is Outline.Generic -> outerOutline.path
        is Outline.Rounded -> Path().apply { addRoundRect(outerOutline.roundRect) }
        is Outline.Rectangle -> Path().apply { addRect(outerOutline.rect) }
    }

    val ringPath = Path.combine(
        PathOperation.Difference,
        outerPath,
        innerPath
    )

    val paint = Paint().apply {
        this.color = color
        asFrameworkPaint().apply {
            isAntiAlias = true
            maskFilter = BlurMaskFilter(blurPx, BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(dx - spreadPx / 2f, dy - spreadPx / 2f)
        canvas.drawPath(ringPath, paint)
        canvas.restore()
    }
}
