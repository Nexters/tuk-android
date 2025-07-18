package com.plottwist.tuk

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureOrbit(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        dependencies {
            add("implementation", libs.findLibrary("orbit.core").get())
            add("implementation", libs.findLibrary("orbit.viewmodel").get())
            add("implementation", libs.findLibrary("orbit.compose").get())
            add("testImplementation", libs.findLibrary("orbit.test").get())
        }
    }
}
