plugins {
    alias(libs.plugins.tuk.android.application)
    alias(libs.plugins.tuk.android.application.compose)
}

android {
    namespace = "com.plottwist.tuk"
}

dependencies {
    testImplementation(libs.junit)
}
