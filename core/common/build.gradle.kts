plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)

}

kotlin {
    task("testClasses")

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
            implementation (libs.co.touchlab.kermit)
            implementation(compose.material3)

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
