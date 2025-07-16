import com.android.build.api.dsl.ApplicationExtension
import com.plottwist.tuk.configureKotlinAndroid
import com.plottwist.tuk.configurePackaging
import com.plottwist.tuk.configureDefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(project.pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig()
                configurePackaging()

                buildFeatures {
                    buildConfig = true
                }

                buildTypes {
                    getByName("release") {
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
            }
        }
    }
}
