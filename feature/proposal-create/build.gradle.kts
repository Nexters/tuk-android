plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.plottwist.feature.proposal_create"
}

dependencies {
    implementation(project(":core:weburl"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
