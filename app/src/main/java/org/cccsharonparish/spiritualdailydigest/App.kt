package org.cccsharonparish.spiritualdailydigest

import android.app.Application
import androidx.compose.ui.graphics.toArgb
import dagger.hilt.android.HiltAndroidApp
import org.cccsharonparish.core.common.helpers.logging.timber.InitializeTimber
import org.cccsharonparish.core.common.helpers.notification.NotificationChannelBuilder
import org.cccsharonparish.core.resources.LightThemeColours
import org.cccsharonparish.core.resources.R

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        InitializeTimber()
        createANotificationChannel()
    }

    private fun createANotificationChannel() {
        val notificationChannel = NotificationChannelBuilder(
            context = this
        )
        notificationChannel.createANotificationChannel(
            notificationDescription = getString(R.string.notification_channel_desc),
            notificationChannelId = getString(R.string.notification_channel_id),
            LightThemeColours.secondary.toArgb()
        )
    }
}