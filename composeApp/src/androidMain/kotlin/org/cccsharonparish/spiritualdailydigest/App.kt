/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.spiritualdailydigest

import android.app.Application
import androidx.compose.ui.graphics.toArgb
import org.cccsharonparish.core.common.helpers.notification.NotificationChannelBuilder
import org.cccsharonparish.core.resources.LightColorScheme
import  org.cccsharonparish.core.resources.R

class App : Application() {

    override fun onCreate() {
        super.onCreate()
      createANotificationChannel()
    }

    private fun createANotificationChannel() {
        val notificationChannel = NotificationChannelBuilder(
            context = this
        )
        notificationChannel.createANotificationChannels(
            notificationDescription = getString(R.string.notification_channel_desc),
            notificationChannelId = getString(R.string.notification_channel_id),
            LightColorScheme.primary.toArgb()
        )
    }
}