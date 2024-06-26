package gradle.plugins

import gradle.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply("dagger.hilt.android.plugin")

            dependencies {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())
                add("kapt", libs.findLibrary("hilt.compiler.androidx").get())
                add("annotationProcessor", libs.findLibrary("hilt.compiler.androidx").get())
            }
        }
    }
}