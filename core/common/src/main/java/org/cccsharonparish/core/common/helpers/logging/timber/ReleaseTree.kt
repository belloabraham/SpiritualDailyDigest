package org.cccsharonparish.core.common.helpers.logging.timber

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import timber.log.Timber
class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logTypeIsNotAnError = priority != Log.ERROR
        if (logTypeIsNotAnError) {
            return
        }

        val thereWasAnError = t != null
        if (thereWasAnError) {
            Firebase.crashlytics.recordException(t!!)
        }
    }
}