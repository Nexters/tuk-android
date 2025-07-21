import com.android.build.api.dsl.LibraryExtension
import com.plottwist.tuk.configureAndroidCompose
import com.plottwist.tuk.configureHilt
import com.plottwist.tuk.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.configure<LibraryExtension> { configureAndroidCompose(this) }

            dependencies {
                add("implementation", libs.findLibrary("appcompat").get())
                add("implementation", libs.findLibrary("material").get())
            }
        }
    }
}
