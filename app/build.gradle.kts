plugins {
    alias(libs.plugins.tuk.android.application)
    alias(libs.plugins.tuk.android.application.compose)
}

android {
    namespace = "com.plottwist.tuk"
}

dependencies {
    implementation(project(":feature:main"))
    testImplementation(libs.junit)
}
