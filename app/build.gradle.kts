plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.officialcocktailapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.officialcocktailapp"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.navigation:navigation-compose:2.8.8")

}

dependencies {
    // Jetpack Compose BOM (Bill of Materials) - gestisce le versioni automaticamente
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))

    // Material 3
    implementation("androidx.compose.material3:material3")

    // Material Icons (per icone come KeyboardArrowUp e Search)
    implementation("androidx.compose.material:material-icons-extended")

    // UI e Foundation di Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Runtime e gestione dello stato
    implementation("androidx.compose.runtime:runtime")

    // ViewModel per Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

    // Activity Compose (necessario per avviare Compose in un'Activity)
    implementation("androidx.activity:activity-compose")

    // Debugging (opzionale, per preview e debugging)
    debugImplementation("androidx.compose.ui:ui-tooling")
}

dependencies {
    // Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Lifecycle components (per i ViewModel e le coroutine)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Jetpack Compose (opzionale, solo se usi componenti UI Compose)
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

    implementation("io.coil-kt:coil-compose:2.5.0")
}
