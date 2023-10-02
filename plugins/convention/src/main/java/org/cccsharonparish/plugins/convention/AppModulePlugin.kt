package org.cccsharonparish.plugins.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AppModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply( "com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.cccsharonparish.compose")
                apply("org.cccsharonparish.dagger")
                apply("org.cccsharonparish.work")
            }

            extensions.configure<ApplicationExtension>{
                configureAndroidAndKotlin(this)

                defaultConfig{
                    targetSdk = Config.TARGET_SDK
                    multiDexEnabled = true
                }

                buildTypes {
                    debug {
                        isDebuggable = true
                    }
                    release {
                        isDebuggable = false
                        isShrinkResources = true
                        multiDexEnabled = true
                    }
                }
            }

            dependencies{
                "implementation"(libs["core.splashscreen"])
                "implementation"(libs["accompanist.systemuicontroller"])
                "debugImplementation"(libs["leakcanary.android"])
            }

        }
    }
}