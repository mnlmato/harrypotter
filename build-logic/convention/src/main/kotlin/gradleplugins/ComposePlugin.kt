package gradleplugins

import com.android.build.api.variant.AndroidComponentsExtension
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            val androidComponents = extensions.getByType(AndroidComponentsExtension::class.java)

            androidComponents.finalizeDsl {
                it.buildFeatures.compose = true
                it.composeOptions.kotlinCompilerExtensionVersion =
                    libs.findVersion("kotlinCompilerExtensionVersion").get().toString()
            }

            dependencies {
                add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                add("implementation", libs.findLibrary("androidx.compose.material3").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.compose.ui.tooling").get())
                add("implementation", libs.findLibrary("glide.compose").get())
            }
        }
    }
}