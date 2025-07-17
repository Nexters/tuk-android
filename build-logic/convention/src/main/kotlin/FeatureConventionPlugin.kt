import com.android.build.api.dsl.LibraryExtension
import com.plottwist.tuk.configureAndroidCompose
import com.plottwist.tuk.configureOrbit
import com.plottwist.tuk.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(project.pluginManager) {
                apply("tuk.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                configureOrbit(this)
            }

            dependencies {
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("core.ktx").get())
                add("implementation", libs.findLibrary("appcompat").get())
                add("implementation", libs.findLibrary("material").get())
                add("implementation", libs.findLibrary("lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("lifecycle.runtime.compose").get())
            }
        }
    }
}
