plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.kotlinx.serialization.plugin)
}

android {
    namespace = "com.plottwist.core.ui.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
