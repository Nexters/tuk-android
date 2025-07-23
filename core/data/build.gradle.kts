plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.plottwist.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:preference"))
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
}
