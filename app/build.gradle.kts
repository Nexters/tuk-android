plugins {
    alias(libs.plugins.tuk.android.application)
    alias(libs.plugins.tuk.android.application.compose)
    alias(libs.plugins.tuk.android.application.hilt)
}

android {
    namespace = "com.plottwist.tuk"
}

dependencies {
    implementation(project(":feature:main"))
    testImplementation(libs.junit)
}
