package com.plottwist.core.designsystem.foundation.type

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.R


private val pretendardFamily = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
)
private val PretendardStyle = TextStyle(
    fontFamily = pretendardFamily
)

val TukPretendardTypography = PretendardTypography(
    title26M = PretendardStyle.copy(
        fontSize = 26.sp,
        lineHeight = 30.sp,
        letterSpacing = (-1.5).sp,
        fontWeight = FontWeight.Medium
    ),
    title24M = PretendardStyle.copy(
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = (-1.5).sp,
        fontWeight = FontWeight.Medium
    ),
    body16R = PretendardStyle.copy(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Normal
    ),
    body14M = PretendardStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Medium
    ),
    body14R = PretendardStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Normal
    ),
    body12B = PretendardStyle.copy(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Bold
    ),
    body12M = PretendardStyle.copy(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Medium
    ),
    body12R = PretendardStyle.copy(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.5).sp,
        fontWeight = FontWeight.Normal
    )
)

@Immutable
data class PretendardTypography(
    val title26M: TextStyle,
    val title24M: TextStyle,
    val body16R: TextStyle,
    val body14M: TextStyle,
    val body14R: TextStyle,
    val body12B: TextStyle,
    val body12M: TextStyle,
    val body12R: TextStyle,
)
