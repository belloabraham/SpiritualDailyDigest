package org.cccsharonparish.plugins.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class WorkPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            dependencies{
                "androidTestImplementation"(libs["work.testing"])
                "implementation"(libs["work.runtime.ktx"])
                "implementation"(libs["hilt.worker"])
            }
        }
    }
}