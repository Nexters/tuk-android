enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("1.0.0")
}

rootProject.name = "tuk"
include(":app")
include(":core:network")
include(":core:preference")
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:ui")
include(":feature:home")
include(":feature:login")
include(":feature:main")
include(":core:ui-navigation")
include(":core:auth-provider")
include(":feature:mypage")
include(":feature:create_gathering")
