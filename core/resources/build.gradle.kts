import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
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
            baseName = "resources"
            isStatic = true
            binaryOption("bundleId", "org.cccsharonparish.core.resources")
            binaryOption("bundleVersion", "1")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(libs.material3.windowsizeclass.multiplatform)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.cccsharonparish.core.resources"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
