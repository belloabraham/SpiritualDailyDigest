import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)

}

kotlin {
    task("testClasses")
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
            isStatic = true
            binaryOption("bundleId", "org.cccsharonparish.core.common")
            binaryOption("bundleVersion", "1")
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)

            implementation(libs.app.update)
            implementation(libs.app.update.ktx)
            implementation(libs.app.review)
            implementation(libs.app.review.ktx)
            implementation (libs.toasty)

        }
        commonMain.dependencies {
            //put your multiplatform dependencies here

            implementation(compose.material3)
            implementation(libs.konnectivity)


            implementation (libs.co.touchlab.kermit)

            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization.json)


            implementation (projects.core.resources)
            implementation(projects.core.data)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.cccsharonparish.core.common"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
