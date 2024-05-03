plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("hiltPlugin") {
            id = "gradle.plugins.convention.di.hilt"
            implementationClass = "gradle.plugins.HiltPlugin"
        }
    }
}