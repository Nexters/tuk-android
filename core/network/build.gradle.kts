plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.android.library.hilt)
    alias(libs.plugins.kotlinx.serialization.plugin)
}

android {
    namespace = "com.plottwist.core.network"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.serialization)

    platform(libs.okhttp.bom).apply {
        implementation(this)
        implementation(libs.okhttp)
        implementation(libs.okhttp.logging)
    }

    testImplementation(libs.junit)
}
