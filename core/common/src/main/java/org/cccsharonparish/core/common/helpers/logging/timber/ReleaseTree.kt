package org.cccsharonparish.core.common.helpers.logging.timber

import android.util.Log
import timber.log.Timber
class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logTypeIsNotAnError = priority != Log.ERROR
        if (logTypeIsNotAnError) {
            return
        }

        val thereIsAnError = t != null
        if (thereIsAnError) {
            //TODO
            // Firebase.crashlytics.recordException(t)
            // Add tags and all of what recordException supports
        }
    }
}