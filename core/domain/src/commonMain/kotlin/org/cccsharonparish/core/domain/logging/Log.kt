package org.cccsharonparish.core.domain.logging

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.crashlytics.crashlytics
import org.cccsharonparish.core.domain.Config

object Log {

    private const val TAG = Config.PROJECT_NAME
    private val crashlytics = Firebase.crashlytics

    fun error(tag: String, throwable: Throwable? = null, message: () -> String){
        Logger.e(tag, throwable, message)
        crashlytics.log(message())
        throwable?.let {
            crashlytics.recordException(throwable)
        }
    }
    fun error(messageString: String, throwable: Throwable? = null, tag: String = TAG){
        Logger.e(messageString, throwable, tag)
        crashlytics.log(messageString)
        throwable?.let {
            crashlytics.recordException(throwable)
        }
    }

    fun error(throwable: Throwable? = null, tag: String = TAG, message: () -> String){
        Logger.e(throwable, tag, message)
        crashlytics.log(message())
        throwable?.let {
            crashlytics.recordException(throwable)
        }
    }

    fun debug(tag: String, throwable: Throwable? = null, message: () -> String){
        Logger.d(tag, throwable, message)
    }

    fun debug(messageString: String, throwable: Throwable? = null, tag: String = TAG){
        Logger.d(messageString, throwable, tag)
    }

    fun debug(throwable: Throwable? = null, tag: String = TAG, message: () -> String){
        Logger.d(throwable, tag, message)
    }

}