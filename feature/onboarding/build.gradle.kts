@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.feature")
}

android {
    namespace = "org.cccsharonparish.feature.onboarding"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:constants"))
    implementation(project(":core:resources"))
}