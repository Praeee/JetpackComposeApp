plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.praeee.feature.news.news_ui"
    compileSdk = 34
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
//    androidTestImplementation(libs.textExtJunit)
    androidTestImplementation(libs.espresso)

    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.swipe.refresh)
    implementation(libs.androidx.material3.android)
}
