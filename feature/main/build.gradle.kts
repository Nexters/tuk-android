plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
}

android {
    namespace = "com.plottwist.feature.main"
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:create_gathering"))
    implementation(project(":feature:gathering-detail"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
