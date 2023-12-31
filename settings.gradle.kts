pluginManagement {
    includeBuild("plugins")
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
        maven ( url = "https://jitpack.io" )
    }
}

rootProject.name = "SpiritualDailyDigest"
include(":app")
include(":core:resources")
include(":core:common")
include(":core:model")
include(":core:ui")
include(":core:constants")
include(":feature:onboarding")
