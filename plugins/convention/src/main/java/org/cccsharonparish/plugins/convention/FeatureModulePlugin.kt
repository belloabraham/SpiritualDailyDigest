package org.cccsharonparish.plugins.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.configure

class FeatureModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target){

            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.cccsharonparish.compose")
            }

            extensions.configure<LibraryExtension>{
                configureAndroidAndKotlin(this)

                defaultConfig{
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            dependencies{
            }
        }

    }
}