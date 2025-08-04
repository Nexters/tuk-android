package com.plottwist.core.designsystem.foundation

import androidx.compose.ui.graphics.Color

object TukColorTokens {
    val Grey000 = Color(0xFFFFFFFF)
    val Grey050 = Color(0xFFFAFAFA)
    val Grey100 = Color(0xFFF5F5F5)
    val Grey200 = Color(0xFFEAEAEA)
    val Grey300 = Color(0xFFE1E1E1)
    val Grey500 = Color(0xFFCCCCCC)
    val Grey700 = Color(0xFFA0A0A0)
    val Grey800 = Color(0xFF888888)
    val Grey900 = Color(0xFF1F1F1F)

    val CoralRed050 = Color(0xFFFFF1F1)
    val CoralRed100 = Color(0xFFFFDFDF)
    val CoralRed200 = Color(0xFFFFC5C5)
    val CoralRed300 = Color(0xFFFF9D9D)
    val CoralRed400 = Color(0xFFFF6464)
    val CoralRed500 = Color(0xFFFF3838)
    val CoralRed600 = Color(0xFFED1515)
    val CoralRed700 = Color(0xFFC80D0D)
    val CoralRed800 = Color(0xFFA50F0F)
    val CoralRed900 = Color(0xFF881414)
    val CoralRed950 = Color(0xFF4B0404)
}

object TukPrimitivesColor {
    val Primary600 = TukColorTokens.CoralRed600
    val Primary500 = TukColorTokens.CoralRed500
    val Primary400 = TukColorTokens.CoralRed400
    val Primary300 = TukColorTokens.CoralRed300
}

object TukTextColor {
    val Primary = TukColorTokens.Grey900
    val Secondary = TukColorTokens.Grey700
    val Tertiary = TukColorTokens.Grey500
    val Disabled = TukColorTokens.Grey000
    val Error = TukColorTokens.CoralRed600
}
