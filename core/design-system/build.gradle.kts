plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.praeee.core.design_system"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.textExtJunit)
    androidTestImplementation(libs.espresso)

    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.swipe.refresh)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview)


}