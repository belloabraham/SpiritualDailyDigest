rootProject.name = "SpiritualDailyDigest"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven ( url = "https://jitpack.io" )
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")
include(":core:auth")
include(":core:data")
include(":core:common")
include(":core:resources")
include(":core:network")
include(":core:domain")
include(":core:ui")
