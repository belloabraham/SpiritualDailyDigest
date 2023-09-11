package org.cccsharonparish.core.common.helpers.logging.timber

import org.cccsharonparish.core.common.BuildConfig
import timber.log.Timber

class InitializeTimber {
    operator fun invoke() {
        val debugTree = if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            ReleaseTree()
        }
        Timber.plant(debugTree)
    }
}