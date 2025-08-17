package com.plottwist.tuk

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun ApplicationExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = "com.plottwist.tuk"
        targetSdk = 36
        versionCode = 5
        versionName = "1.0.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

fun CommonExtension<*, *, *, *, *, *>.configurePackaging() {
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun CommonExtension<*, *, *, *, *, *>.configureJavaCompatibility() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
}

val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
