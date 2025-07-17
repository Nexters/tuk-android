package com.plottwist.tuk

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        with(pluginManager) {
            apply("dagger.hilt.android.plugin")
            apply("com.google.devtools.ksp")
        }

        dependencies {
            add("implementation", libs.findLibrary("hilt.android").get())
            add("ksp", libs.findLibrary("hilt.compiler").get())
        }
    }
}
