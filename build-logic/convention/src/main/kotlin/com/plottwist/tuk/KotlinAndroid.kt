package com.plottwist.tuk

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_18
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, * ,*, *>
) {
    commonExtension.apply {
        compileSdk = 36

        defaultConfig {
            minSdk = 26

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            buildConfig = true
        }

        configureJavaCompatibility()

        configurePackaging()

        configureKotlin()
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JVM_18)
        }
    }
}
