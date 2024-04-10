/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.logging

import co.touchlab.kermit.Logger
import org.cccsharonparish.core.data.constants.Config

object Log {

    private const val TAG = Config.PROJECT_NAME

    fun error(tag: String, throwable: Throwable? = null, message: () -> String){
        Logger.e(tag, throwable, message)
    }
    fun error(messageString: String, throwable: Throwable? = null, tag: String = TAG){
        Logger.e(messageString, throwable, tag)
    }

    fun error(throwable: Throwable? = null, tag: String = TAG, message: () -> String){
        Logger.e(throwable, tag, message)
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