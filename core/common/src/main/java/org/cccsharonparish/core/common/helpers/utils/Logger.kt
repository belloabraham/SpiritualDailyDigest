package org.cccsharonparish.core.common.helpers.utils

import timber.log.Timber

object Logger {

    fun error(throwable:Throwable){
        Timber.e(throwable)
    }

    fun error(throwable:Throwable, message:String, args:Any){
        Timber.e(throwable, message, args)
    }

}