plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.compose)
}

android {
    namespace = "com.plottwist.core.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
