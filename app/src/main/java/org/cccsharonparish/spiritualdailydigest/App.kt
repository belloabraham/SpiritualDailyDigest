package org.cccsharonparish.spiritualdailydigest

import android.app.Application
import org.cccsharonparish.core.common.helpers.logging.timber.InitializeTimber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        InitializeTimber()
    }
}