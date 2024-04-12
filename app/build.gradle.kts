plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.dagger.hilt.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {

    namespace = "com.harrypotter"

    compileSdk = 34
    defaultConfig {
        applicationId = "com.harrypotter"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.harrypotter.runners.CustomTestApplicationRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }

    lint {
        lintConfig = file("$rootDir/lint-ignored.xml")
    }

    dependencies {
        // Architecture components
        implementation(libs.androidx.activity)
        implementation(libs.androidx.core)
        implementation(libs.android.lifecycle.extensions)
        implementation(libs.android.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.compose.livedata)

        // DI
        implementation(libs.hilt.android)
        kapt(libs.hilt.compiler)
        kapt(libs.hilt.compiler.androidx)
        annotationProcessor(libs.hilt.compiler.androidx)

        // Network
        implementation(libs.retrofit)
        implementation(libs.coroutines.core)
        implementation(libs.okhttp3)
        implementation(libs.okhttp3.logging.interceptor)
        implementation(libs.retrofit.converter.gson)
        implementation(libs.google.gson)

        // Unit Testing
        testImplementation(libs.junit)
        testImplementation(libs.androidx.test.junit.ktx)
        testImplementation(libs.coroutines.test)
        testImplementation(libs.androidx.core.testing)
        testImplementation(libs.mockk)
        testImplementation(libs.kotest)
        androidTestImplementation(libs.androidx.test.junit.ext)

        // UI testing
        androidTestImplementation(libs.androidx.test.espresso.core)
        androidTestImplementation(libs.androidx.test.espresso.contrib)
        androidTestImplementation(libs.androidx.test.espresso.intents)
        androidTestImplementation(libs.androidx.test.core.ktx)
        androidTestImplementation(libs.androidx.test.junit.ktx)
        androidTestImplementation(libs.hilt.android.testing)
        kaptAndroidTest(libs.hilt.android.compiler)
        androidTestImplementation(libs.okhttp3.mockwebserver)
        androidTestImplementation(libs.espresso.okhttp3.idling.resource)

        // Test rules and transitive dependencies:
        androidTestImplementation(libs.androidx.compose.ui)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.test.monitor)

        val composeBom = platform(libs.androidx.compose.bom)
        implementation(composeBom)
        androidTestImplementation(composeBom)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui.tooling.preview)
        debugImplementation(libs.androidx.compose.ui.tooling)
        implementation(libs.glide.compose)
        implementation(libs.androidx.appcompat)
        implementation(libs.google.android.material)
    }
}

kapt {
    correctErrorTypes = true
}

configurations.all {
    resolutionStrategy {
        force(libs.androidx.test.core.ktx)
    }
}