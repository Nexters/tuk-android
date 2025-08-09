plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.hilt)
}

android {
    namespace = "com.plottwist.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:preference"))
    implementation(project(":core:domain"))
    implementation(project(":core:push"))

    implementation(libs.retrofit)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
}
