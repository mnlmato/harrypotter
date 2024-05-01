plugins {
     `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.tools.build.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("hiltDependencyInjectionPlugin") {
            id = "gradlePlugins.convention.di.hilt"
            implementationClass = "gradleplugins.HiltDependencyInjectionPlugin"
        }
    }
}