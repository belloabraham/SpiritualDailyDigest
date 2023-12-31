
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "org.cccsharonparish.plugins.convention"


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies{
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("applicationModulesPlugin") {
            id = "org.cccsharonparish.application"
            implementationClass = "org.cccsharonparish.plugins.convention.AppModulePlugin"
        }
        register("coreModulesPlugin") {
            id = "org.cccsharonparish.core"
            implementationClass = "org.cccsharonparish.plugins.convention.CoreModulePlugin"
        }
        register("workPlugin") {
            id = "org.cccsharonparish.work"
            implementationClass = "org.cccsharonparish.plugins.convention.WorkPlugin"
        }
        register("featureModulesPlugin") {
            id = "org.cccsharonparish.feature"
            implementationClass = "org.cccsharonparish.plugins.convention.FeatureModulePlugin"
        }

        register("daggerPlugin") {
            id = "org.cccsharonparish.dagger"
            implementationClass = "org.cccsharonparish.plugins.convention.DaggerHiltPlugin"
        }

        register("composePlugin") {
            id = "org.cccsharonparish.compose"
            implementationClass = "org.cccsharonparish.plugins.convention.ComposePlugin"
        }
    }
}