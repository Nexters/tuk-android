import com.android.build.api.dsl.ApplicationExtension
import com.plottwist.tuk.configureDefaultConfig
import com.plottwist.tuk.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig()

                buildTypes {
                    getByName("release") {
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                flavorDimensions += "stage"
                productFlavors {
                    val localProperties = Properties().apply {
                        load(FileInputStream(File(project.rootDir.absolutePath + File.separator + "local.properties")))
                    }
                    create("production") {
                        dimension = "stage"
                        buildConfigField(
                            "String",
                            "TUK_BASE_URL",
                            "\"${localProperties.getProperty("TUK_BASE_URL")}\""
                        )
                        buildConfigField(
                            "String",
                            "GOOGLE_CLIENT_ID",
                            "\"${localProperties.getProperty("GOOGLE_CLIENT_ID")}\""
                        )
                        buildConfigField(
                            "String",
                            "TUK_SENT_PROPOSAL_URL",
                            "\"${localProperties.getProperty("TUK_SENT_PROPOSAL_URL")}\""
                        )
                        buildConfigField(
                            "String",
                            "TUK_PROPOSALS_URL",
                            "\"${localProperties.getProperty("TUK_PROPOSALS_URL")}\""
                        )
                        buildConfigField(
                            "String",
                            "TUK_INVITE_GATHERING_URL",
                            "\"${localProperties.getProperty("TUK_INVITE_GATHERING_URL")}\""
                        )

                    }
                }
            }
        }
    }
}
