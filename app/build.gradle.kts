plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }

    dependencies {
        // Architecture components
        implementation("androidx.activity:activity-ktx:1.7.0")
        implementation("androidx.core:core-ktx:1.9.0")
        implementation("android.arch.lifecycle:extensions:1.1.1")
        implementation("android.arch.lifecycle:viewmodel:1.1.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
        // Workaround: Added navigation dependency to avoid the IllegalArgumentException: CreationExtras must have a value by SAVED_STATE_REGISTRY_OWNER_KEY'.
        implementation("androidx.navigation:navigation-compose:2.5.3")
        implementation("androidx.compose.runtime:runtime-livedata:1.4.0")

        // DI
        implementation("com.google.dagger:hilt-android:2.45")
        kapt("com.google.dagger:hilt-compiler:2.45")
        kapt("androidx.hilt:hilt-compiler:1.0.0")
        annotationProcessor("androidx.hilt:hilt-compiler:1.0.0")

        // Network
        implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("com.squareup.okhttp3:okhttp:4.2.1")
        implementation("com.squareup.okhttp3:logging-interceptor:4.2.1")
        implementation("com.squareup.retrofit2:converter-gson:2.8.1")
        implementation("com.google.code.gson:gson:2.8.9")

        // Unit Testing
        testImplementation("junit:junit:4.13.2")
        testImplementation("androidx.test.ext:junit-ktx:1.1.5")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        testImplementation("androidx.arch.core:core-testing:2.2.0")
        testImplementation("io.mockk:mockk:1.10.0")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:4.1.3")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")

        // Espresso dependencies - compose setup: https://developer.android.com/jetpack/compose/testing?hl=es-419
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
        androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
        androidTestImplementation("androidx.test:core-ktx:1.4.0")
        androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
        androidTestImplementation("com.google.dagger:hilt-android-testing:2.34.1-beta")
        kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.34.1-beta")
        androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.2.1")
        androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")

        // Test rules and transitive dependencies:
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0")
        // Needed for createAndroidComposeRule, but not createComposeRule:
        debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.0")
        debugImplementation("androidx.test:monitor:1.6.1")

        // UI - Compose setup: https://developer.android.com/jetpack/compose/setup?hl=es-419
        val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
        implementation(composeBom)
        androidTestImplementation(composeBom)
        implementation("androidx.compose.material3:material3")
        implementation("androidx.compose.ui:ui-tooling-preview")
        debugImplementation("androidx.compose.ui:ui-tooling")
        implementation("com.github.bumptech.glide:compose:1.0.0-alpha.1")

        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.8.0")
    }
}

kapt {
    correctErrorTypes = true
}
