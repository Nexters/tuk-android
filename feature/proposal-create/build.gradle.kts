plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
}

android {
    namespace = "com.plottwist.feature.proposal_create"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
