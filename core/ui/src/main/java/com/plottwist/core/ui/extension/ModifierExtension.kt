package com.plottwist.core.ui.extension

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.05f),
    blur: Dp = 8.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.drawBehind {

    val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
    val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

    val paint = Paint()
    paint.color = color

    if (blur.toPx() > 0) {
        paint.asFrameworkPaint().apply {
            maskFilter = BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL)
        }
    }

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(offsetX.toPx(), offsetY.toPx())
        canvas.drawOutline(shadowOutline, paint)
        canvas.restore()
    }
}

fun Modifier.borderExceptBottom(
    shape: Shape,
    width: Dp,
    color: Color
) = drawBehind {
    val strokeW = width.toPx()

    // 1) Shape → Path
    val path = when (val outline = shape.createOutline(size, layoutDirection, this)) {
        is Outline.Rounded   -> Path().apply { addRoundRect(outline.roundRect) }
        is Outline.Rectangle -> Path().apply { addRect(outline.rect) }
        is Outline.Generic   -> outline.path
    }

    // 2) 아래쪽 영역을 잘라내고(clip) Stroke로 그림
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height - strokeW / 2f // 하단 절반만큼은 안보이게
    ) {
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeW)
        )
    }
}
