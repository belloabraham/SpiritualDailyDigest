@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.core")
}

android {
    namespace = "org.cccsharonparish.core.common"
}

dependencies {
    implementation(libs.timber)
    implementation (libs.toasty)

    implementation (libs.hilt.android)

    implementation(libs.app.update)
    implementation(libs.app.update.ktx)
    implementation(libs.app.review)
    implementation(libs.app.review.ktx)

    implementation(libs.datastore.preferences)

    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.crashlytics)

    implementation(project(":core:resources"))

}