plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

// Remove the `buildscript` block entirely, as modern Gradle projects use `dependencyResolutionManagement` in `settings.gradle.kts`.

allprojects {
    // Remove this block as it violates the central repository declaration in `settings.gradle.kts`.
    // Repository management is now centralized in `settings.gradle.kts`.
}

