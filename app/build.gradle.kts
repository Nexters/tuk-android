plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.jetpack.compose.compiler)
    alias(libs.plugins.tuk.android.application)
    alias(libs.plugins.tuk.android.application.compose)
}

android {
    namespace = "com.plottwist.tuk"
}

dependencies {
    testImplementation(libs.junit)
}
