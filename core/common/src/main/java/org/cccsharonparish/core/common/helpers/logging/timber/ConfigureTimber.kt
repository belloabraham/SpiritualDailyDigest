package org.cccsharonparish.core.common.helpers.logging.timber

import timber.log.Timber

class ConfigureTimber(isDebugMode:Boolean) {

    init {
        val tree = if (isDebugMode) {
            Timber.DebugTree()
        } else {
            ReleaseTree()
        }
        Timber.plant(tree)
    }
}