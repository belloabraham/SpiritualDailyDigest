@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.core")
}

android {
    namespace = "org.cccsharonparish.core.common"
}

dependencies {
    implementation(libs.timber)
    implementation(project(":core:resources"))
}