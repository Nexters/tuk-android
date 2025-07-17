plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
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
    }
}
