import com.android.build.api.dsl.ApplicationExtension
import com.plottwist.tuk.configureHilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationHiltConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            extensions.configure<ApplicationExtension> { configureHilt(this) }
        }
    }
}
