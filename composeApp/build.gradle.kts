import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.co.touchlab.skie)
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
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "org.cccsharonparish.spiritualdailydigest")
            binaryOption("bundleVersion", "1")
        }
    }

     sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.core.splashscreen)

            implementation(dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.android)

            api(libs.androidx.startup)

            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)

        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(libs.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)

            implementation(dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.core.coroutines)
            implementation(libs.koin.test)


            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.koin)


            implementation(libs.material3.windowsizeclass.multiplatform)
            implementation(libs.androidx.datastore.preferences.core)

            implementation (libs.co.touchlab.skie.annotations)

            implementation (projects.core.resources)
            implementation (projects.core.common)
            implementation (projects.core.data)
            implementation (projects.feature.onboarding)
        }
    }
}

android {
    namespace = "org.cccsharonparish.spiritualdailydigest"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.cccsharonparish.spiritualdailydigest"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

