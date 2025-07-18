plugins {
    alias(libs.plugins.tuk.android.library)
}

android {
    namespace = "com.plottwist.core.preference"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
