package org.cccsharonparish.plugins.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            dependencies{
                "androidTestImplementation"(platform(libs["compose.bom"]))
                "implementation"(libs["lifecycle.runtime.ktx"])
                "implementation"(libs["activity.compose"])
                "implementation"(platform(libs["compose.bom"]))
                "implementation"(libs["ui"])
                "implementation"(libs["material.ripple"])
                "implementation"(libs["ui.graphics"])
                "implementation"(libs["ui.tooling.preview"])
                "implementation"(libs["material"])

                "debugImplementation"(libs["ui.tooling"])
                "debugImplementation"(libs["ui.test.manifest"])
            }
        }
    }
}