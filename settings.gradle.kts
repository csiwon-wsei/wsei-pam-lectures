pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.3.2" apply false
        id("org.jetbrains.kotlin.android") version "2.1.0" apply false
        id("org.jetbrains.compose") version "1.5.12" apply false
        id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lectures"
include(":app")
