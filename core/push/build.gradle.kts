plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.hilt)
    alias(libs.plugins.google.service)
}

android {
    namespace = "com.plottwist.core.push"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.retrofit)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
}
