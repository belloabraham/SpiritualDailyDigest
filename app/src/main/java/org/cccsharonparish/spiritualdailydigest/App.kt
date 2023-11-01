package org.cccsharonparish.spiritualdailydigest

import android.app.Application
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import org.cccsharonparish.core.common.helpers.logging.timber.ConfigureTimber
import org.cccsharonparish.core.common.helpers.notification.NotificationChannelBuilder
import org.cccsharonparish.core.resources.ui.theme.LightThemeColours
import org.cccsharonparish.core.resources.R
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        ConfigureTimber(BuildConfig.DEBUG)
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