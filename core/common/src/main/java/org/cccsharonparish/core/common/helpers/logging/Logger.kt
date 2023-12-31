package org.cccsharonparish.core.common.helpers.logging

import timber.log.Timber
import java.lang.Exception

object Logger {

    fun logError(throwable: Throwable) {
        Timber.e(throwable)
    }

    fun logError(throwable: Throwable, message: String, args: Any) {
        Timber.e(throwable, message, args)
    }

    fun logError(exception: Exception, message: String, args: Any) {
        Timber.e(Throwable(exception), message, args)
    }

    fun logError(exception: Exception) {
        Timber.e(Throwable(exception))
    }

    fun logWarning(message: String, args: Any) {
        Timber.w(message, args)
    }

    fun debug(throwable: Throwable) {
        Timber.d(throwable)
    }

    fun debug(throwable: Throwable, message: String, args: Any) {
        Timber.d(throwable, message, args)
    }

    fun debug(message: String, args: Any) {
        Timber.d(message, args)
    }

}