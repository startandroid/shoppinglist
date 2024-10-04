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
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shopping List"
include(":app")
include(":core")
include(":core:ui")
include(":core:navigation")
include(":core:database")
include(":core:common")
include(":feature")
include(":feature:catalog")
include(":feature:catalog:api")
include(":feature:catalog:view")
include(":feature:catalog:domain")
include(":feature:catalog:data")
