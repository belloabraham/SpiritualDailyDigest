/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.helpers.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RawRes
import androidx.annotation.RequiresApi

class NotificationChannelBuilder(
    private val context: Context,
) {

    fun createANotificationChannels(
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

    private fun channelSettings(
        notificationChannel: NotificationChannel,
        description: String,
        color: Int,
        @RawRes customSoundRes: Int? = null
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = color
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.description = description
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                notificationChannel.setAllowBubbles(true)
            }
            setCustomSound(customSoundRes, notificationChannel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setCustomSound(@RawRes customSoundRes: Int?, notificationChannel: NotificationChannel){
        val customSoundExist = customSoundRes != null
        if(customSoundExist){
            val soundPath =
                Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/$customSoundRes")
            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.setSound(soundPath, attributes)
        }
    }
}