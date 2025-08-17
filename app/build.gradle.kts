plugins {
    alias(libs.plugins.tuk.android.application)
    alias(libs.plugins.tuk.android.application.compose)
    alias(libs.plugins.tuk.android.application.hilt)
    alias(libs.plugins.google.service)
}

android {
    namespace = "com.plottwist.tuk"
}

dependencies {
    implementation(project(":feature:main"))
    implementation(project(":feature:login"))
    implementation(project(":feature:create_gathering"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:auth-provider"))
    implementation(project(":core:weburl"))
    implementation(project(":core:push"))
    testImplementation(libs.junit)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
}
