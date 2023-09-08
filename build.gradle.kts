// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.hiltAndroid) apply false
}
true // Needed to make the Suppress annotation work for the plugins block