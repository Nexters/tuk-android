package com.plottwist.tuk

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose (
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    with(plugins) {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        val composeBom = libs.findLibrary("compose.bom").get()
        add("implementation", platform(composeBom))
        add("implementation", libs.findLibrary("ui").get())
        add("implementation", libs.findLibrary("ui.tooling.preview").get())
        add("implementation", libs.findLibrary("material3").get())
        add("implementation", libs.findLibrary("ui.graphics").get())
        add("debugImplementation", libs.findLibrary("ui.tooling").get())
        add("debugImplementation", libs.findLibrary("ui.test.manifest").get())
        add("androidTestImplementation", platform(composeBom))
        add("androidTestImplementation", libs.findLibrary("ui.test.junit4").get())
    }
}
