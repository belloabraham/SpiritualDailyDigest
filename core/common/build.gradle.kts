@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.core")
}

android {
    namespace = "org.cccsharonparish.core.common"
}

dependencies {
    implementation(libs.timber)
    testImplementation ("org.mockito:mockito-android:5.5.0")
}