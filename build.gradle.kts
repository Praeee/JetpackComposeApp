// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.android.library") version ("8.0.1") apply (false)
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
}

buildscript {
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.android.hilt.android.gradle.plugin)
        classpath(libs.kotlin.gradlePlugin)
//        classpath(libs.realm)
//        classpath "io.realm:realm-gradle-plugin:10.11.0"
    }

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

