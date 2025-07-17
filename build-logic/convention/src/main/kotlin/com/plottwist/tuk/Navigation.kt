package com.plottwist.tuk

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureUiNavigation(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        dependencies {
            add("implementation", libs.findLibrary("navigation.runtime.ktx").get())
            add("implementation", libs.findLibrary("navigation.compose").get())
            add("implementation", libs.findLibrary("hilt.navigation.compose").get())
        }
    }
}
