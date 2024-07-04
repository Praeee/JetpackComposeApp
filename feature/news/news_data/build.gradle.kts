plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.praeee.feature.news.news_data"
    compileSdk = 34
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.textExtJunit)
    androidTestImplementation(libs.espresso)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.loggingIntercepter)
}