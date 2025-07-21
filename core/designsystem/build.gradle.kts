plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.compose)
}

android {
    namespace = "com.plottwist.core.designsystem"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
