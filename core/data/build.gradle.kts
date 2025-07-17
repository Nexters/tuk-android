plugins {
    alias(libs.plugins.tuk.android.library)
}

android {
    namespace = "com.plottwist.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:preference"))
    implementation(project(":core:domain"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
