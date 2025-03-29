group = rootProject.group
version = rootProject.version

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10" // Kotlin JVM Plugin
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10" // Serialization Plugin
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10") // Kotlin reflection
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2") // Serialization library
}
