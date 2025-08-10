plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.hilt)
}

android {
    namespace = "com.plottwist.core.notification"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.retrofit)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
}
