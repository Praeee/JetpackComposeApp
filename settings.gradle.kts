pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JetpackComposeApp"
include(":app")
include(":feature:coin:data")
include(":feature:coin:domain")
include(":feature:coin:ui")
include(":core:network")
include(":core:common")
include(":core:feature_api")
