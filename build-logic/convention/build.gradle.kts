plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConventionPlugin") {
            id = "tuk.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationComposeConventionPlugin")  {
            id = "tuk.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("AndroidLibraryConventionPlugin")  {
            id = "tuk.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("FeatureConventionPlugin")  {
            id = "tuk.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("AndroidLibraryHiltConventionPlugin") {
            id = "tuk.android.library.hilt"
            implementationClass = "AndroidLibraryHiltConventionPlugin"
        }
        register("AndroidApplicationHiltConventionPlugin") {
            id = "tuk.android.application.hilt"
            implementationClass = "AndroidApplicationHiltConventionPlugin"
        }
        register("AndroidLibraryComposeConventionPlugin") {
            id = "tuk.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

    }
}
