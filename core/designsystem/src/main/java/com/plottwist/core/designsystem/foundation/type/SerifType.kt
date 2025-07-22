package com.plottwist.core.designsystem.foundation.type

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.R


private val notoSerifKrFamily = FontFamily(
    Font(R.font.noto_serif_kr_medium, FontWeight.Medium),
    Font(R.font.noto_serif_kr_regular, FontWeight.Normal),
)
private val SerifStyle = TextStyle(
    fontFamily = notoSerifKrFamily
)

val TukSerifTypography = SerifTypography(
    title24M = SerifStyle.copy(
        fontSize = 24.sp,
        lineHeight = 33.sp,
        letterSpacing = (-1.5).sp,
        fontWeight = FontWeight.Medium
    ),
    title22M = SerifStyle.copy(
        fontSize = 22.sp,
        lineHeight = 30.sp,
        letterSpacing = (-1.5).sp,
        fontWeight = FontWeight.Medium
    ),
    body16M = SerifStyle.copy(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = (-1).sp,
        fontWeight = FontWeight.Normal
    ),
    body14M = SerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-1).sp,
        fontWeight = FontWeight.Normal
    )
)

@Immutable
data class SerifTypography(
    val title24M: TextStyle,
    val title22M: TextStyle,
    val body16M: TextStyle,
    val body14M: TextStyle,
)
