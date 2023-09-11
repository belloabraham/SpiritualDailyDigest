package org.cccsharonparish.plugins.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureAndroidAndKotlin(extension: CommonExtension<*, *, *, *, *>) {

    with(extension) {
        compileSdk = Config.COMPILE_SDK

        lint {
            checkReleaseBuilds = false
            abortOnError = false
        }

        defaultConfig {
            minSdk = Config.MINIMUM_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildFeatures {
            compose = true
            buildConfig = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.7"
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                isJniDebuggable = true
            }
            getByName("release") {
                isMinifyEnabled = true
                isJniDebuggable = false
                isRenderscriptDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            jvmTarget = Config.JAVA_VERSION.toString()
            freeCompilerArgs = freeCompilerArgs + Config.FREE_COMPILER_ARGS
        }

        packaging.resources.excludes.addAll(
            listOf(
                "META-INF/library_release.kotlin_module",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt"
            )
        )

        with(dependencies) {
            //Required by convention plugin
            add("coreLibraryDesugaring", libs["android.desugarJdkLibs"])

            //Dependencies common to App, Feature, and Core Modules
            add("implementation", libs["core.ktx"])

            //--Test dependencies
            add("androidTestImplementation", libs["androidx.test.ext.junit"])
            add("androidTestImplementation", libs["espresso.core"])
            add("androidTestImplementation", libs["ui.test.junit4"])
            add("testImplementation", libs["junit"])
            add("testImplementation", libs["com.google.truth"])

        }
    }
}

private fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

object Config {
    const val MINIMUM_SDK = 24
    const val TARGET_SDK = 34
    const val COMPILE_SDK = 34
    val JAVA_VERSION = JavaVersion.VERSION_17
    val FREE_COMPILER_ARGS = listOf("-opt-in=kotlin.RequiresOptIn")
}