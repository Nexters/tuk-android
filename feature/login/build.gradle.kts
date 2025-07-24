import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.tuk.android.library)
    alias(libs.plugins.tuk.feature)
}

android {
    namespace = "com.plottwist.feature.login"

    buildTypes {
        debug {
            buildConfigField("String", "GOOGLE_CLIENT_ID", "\"${getGoogleClientId()}\"")

        }
        release {
            buildConfigField("String", "GOOGLE_CLIENT_ID", "\"${getGoogleClientId()}\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.google.android.gms:play-services-auth:21.0.0")
}

fun getGoogleClientId(): String {
    return gradleLocalProperties(rootDir, providers).getProperty("GOOGLE_CLIENT_ID") ?: ""
}
