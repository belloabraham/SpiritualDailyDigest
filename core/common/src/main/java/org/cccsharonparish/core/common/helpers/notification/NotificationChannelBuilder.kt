package org.cccsharonparish.core.common.helpers.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationChannelBuilder(
    private val context: Context,
) {

    fun createNotificationChannels(
        notificationDescription:String,
        notificationChannelId:String,
        color:Int
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                notificationChannelId, notificationDescription,
                NotificationManager.IMPORTANCE_HIGH
            )
            channelSettings(notificationChannel, notificationDescription, color)
            val nManager = context.getSystemService(
                NotificationManager::class.java
            )!!
            nManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun channelSettings(nChannel: NotificationChannel, description: String, color:Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nChannel.enableLights(true)
            nChannel.lightColor =  color
            nChannel.enableVibration(true)
            nChannel.setShowBadge(true)
            nChannel.description = description
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                nChannel.setAllowBubbles(true)
            }
        }
    }
}