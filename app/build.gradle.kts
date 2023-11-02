@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.application")
    //Firebase
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "org.cccsharonparish.spiritualdailydigest"

    //Required by android 12 splashscreen api
    compileSdkPreview = "UpsideDownCake"

    defaultConfig {
        applicationId = "org.cccsharonparish.spiritualdailydigest"
        versionCode = 1
        versionName = "1.0.0"
    }

}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:common"))
    implementation(project(":core:constants"))
    implementation(project(":feature:onboarding"))

    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.analytics)
    implementation (libs.firebase.crashlytics)
}
kapt {
    correctErrorTypes = true
}