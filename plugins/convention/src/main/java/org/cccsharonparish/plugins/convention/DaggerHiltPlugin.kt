package org.cccsharonparish.plugins.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DaggerHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){

            with(pluginManager) {
                apply("kotlin-kapt")
                apply("com.google.dagger.hilt.android")
            }

            dependencies{
                "implementation"(libs["hilt.android"])
                "implementation"(libs["hilt.navigation"])
                "kapt"(libs["hilt.android.compiler"])
                "kapt"(libs["hilt.compiler"])

                // For Robolectric tests.
                "testImplementation"(libs["hilt.android.testing"])
                "kaptTest"(libs["hilt.compiler"])

                // For instrumented tests.
                "androidTestImplementation"(libs["hilt.android.testing"])
                "kaptAndroidTest"(libs["hilt.compiler"])
            }
        }
    }
}