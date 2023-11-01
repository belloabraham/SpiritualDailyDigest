
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("org.cccsharonparish.core")
}

android {
    namespace = "org.cccsharonparish.core.resources"
}
dependencies{
    implementation(libs.accompanist.systemuicontroller)
//    implementation(libs.material3.windowSizeClass)
}