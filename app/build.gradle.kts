plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    alias(libs.plugins.google.firebase.appdistribution)
//    alias(libs.plugins.android.application)
//    id("org.jetbrains.kotlin.android")
//    id("com.google.dagger.hilt.android")
//    kotlin("kapt")
}

android {
    namespace = "com.praeee.jetpackcomposeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.praeee.jetpackcomposeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        android.buildFeatures.buildConfig = true

        val flavorName = "dev"
        val newsApiKey = getNewsApiKey(flavorName,rootProject)
        this.buildConfigField("String","NEWS_API_KEY","\"${newsApiKey}\"")

    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotesFile = "/path/to/releasenotes.txt"
//                testers="pornpailin.char@gmail.com"
            }
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
//    productFlavors {
//        getByName("debug") {
//            dimension = "1.0"
//            firebaseAppDistribution {
//                artifactType = "APK"
//                releaseNotes = "Release notes for demo version"
////                testers = "pornpailin.char@gmail.com"
//            }
//        }
//        getByName("release") {
//            dimension = "1.0"
//            firebaseAppDistribution {
//                artifactType = "APK"
//                releaseNotes = "Release notes for full version"
////                testers = "pornpailin.char@gmail.com"
//            }
//        }
//    }

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.loggingIntercepter)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
    implementation(libs.splashscreen)
    implementation(libs.glide)
    annotationProcessor(libs.glide.complier)
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.swipe.refresh)
    implementation(libs.androidx.material3.android)
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    testImplementation("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:4.0.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(platform(libs.firebase.bom))
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
    mapDiagnosticLocations = true
    javacOptions {
        option("-Adagger.hilt.disableCrossCompilationRootValidation=true")
    }
}
