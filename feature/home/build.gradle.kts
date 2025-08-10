plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
}

android {
    namespace = "com.plottwist.feature.home"
}

dependencies {
    implementation(project(":core:weburl"))
    implementation(libs.accompanist.permissions)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
