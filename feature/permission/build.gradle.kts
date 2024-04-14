plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "permission"
            isStatic = true
            binaryOption("bundleId", "org.cccsharonparish.feature.permission")
            binaryOption("bundleVersion", "1")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {

            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(libs.material3)
            implementation(compose.components.resources)
            implementation(compose.runtime)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.material3.windowsizeclass.multiplatform)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenmodel)

            implementation(projects.core.resources)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.cccsharonparish.feature.permission"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
