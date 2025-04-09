// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // Necesario para algunos plugins
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}

// Esto debe ir después del bloque buildscript
plugins {
    id("com.diffplug.spotless") version "6.20.0" apply false // Ejemplo adicional
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}