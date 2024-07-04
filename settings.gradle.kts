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
include(":feature:news:news_data")
include(":feature:news:news_domain")
include(":feature:news:news_ui")
include(":core:design-system")
