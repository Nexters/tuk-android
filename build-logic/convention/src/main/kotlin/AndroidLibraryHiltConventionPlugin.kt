import com.android.build.api.dsl.LibraryExtension
import com.plottwist.tuk.configureHilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryHiltConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.configure<LibraryExtension> { configureHilt(this) }
        }
    }
}
