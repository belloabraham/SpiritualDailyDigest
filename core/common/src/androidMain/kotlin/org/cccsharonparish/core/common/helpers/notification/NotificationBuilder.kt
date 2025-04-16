/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.helpers.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.cccsharonparish.core.resources.R
import org.cccsharonparish.core.common.helpers.utils.IconUtil
import org.cccsharonparish.core.resources.LightColorScheme


class NotificationBuilder(
    private val context: Context,
    private val notificationColor: Int = LightColorScheme.primary.toArgb(),
    @DrawableRes private val smallIcon: Int = R.drawable.small_notification_icon,
    private val notificationChannelId: String = context.getString(
        R.string.notification_channel_id
    ),
    private var largeIcon: Bitmap = IconUtil.getBitmap(context, R.drawable.large_notification_icon)
) {

    private var uri: String? = null
    private var actionText: String? = null
    private lateinit var title: String
    private lateinit var message: String
    private var autoCancel = true
    private var onGoing = false
    private var actionIntent: PendingIntent? = null
    private var priority = NotificationCompat.PRIORITY_HIGH
    private var contentIntent: PendingIntent? = null
    private var alertOnlyOnce = true
    private var actionIntentIcon = 0
    private var vibrationPattern = longArrayOf(0, 200, 100, 300, 100, 300, 100, 300)

    private fun getViewPendingIntent(intent: Intent, context: Context): PendingIntent? {
        return PendingIntent.getActivity(context, 0, intent, getIntentFlag())
    }

    /**
     * Defaults to true
     */
    fun setAlertOnlyOnce(value: Boolean): NotificationBuilder {
        alertOnlyOnce = value
        return this
    }

    fun setVibrationPattern(pattern:LongArray): NotificationBuilder {
        vibrationPattern = pattern
        return this
    }

    private fun getActionIntent(): PendingIntent? {
        return actionIntent
    }

    fun setPriority(value: Int): NotificationBuilder {
        priority = value
        return this
    }

    fun setActionIntent(
        pendingIntent: PendingIntent,
        action: String,
        actionIntentIcon: Int = 0
    ): NotificationBuilder {
        this.actionIntent = pendingIntent
        this.actionIntentIcon = actionIntentIcon
        actionText = action
        return this
    }

    private fun getContentIntent(): PendingIntent? {
        return contentIntent
    }

    fun setContentIntent(pendingIntent: PendingIntent): NotificationBuilder {
        contentIntent = pendingIntent
        return this
    }

    /**
     * Defaults to false
     */
    fun setOngoing(value: Boolean): NotificationBuilder {
        onGoing = value
        return this
    }

    fun setAutoCancel(value: Boolean): NotificationBuilder {
        autoCancel = value
        return this
    }

    fun setContentText(@StringRes value: Int): NotificationBuilder {
        setContentText(getString(value))
        return this
    }

    fun setContentText(value: String): NotificationBuilder {
        message = value
        return this
    }

    fun setTitle(@StringRes value: Int): NotificationBuilder {
        setTitle(getString(value))
        return this
    }

    fun setTitle(value: String): NotificationBuilder {
        title = value
        return this
    }

    fun setLargeIcon(@DrawableRes value: Int): NotificationBuilder {
        largeIcon = IconUtil.getBitmap(context, value)
        return this
    }

    fun setLargeIcon(value: Bitmap): NotificationBuilder {
        largeIcon = value
        return this
    }

    fun setActionText(@StringRes value: Int): NotificationBuilder {
        setActionText(getString(value))
        return this
    }

    fun setActionText(value: String): NotificationBuilder {
        this.actionText = value
        return this
    }

    fun setUri(@StringRes value: Int): NotificationBuilder {
        setUri(getString(value))
        return this
    }

    fun setUri(value: String): NotificationBuilder {
        this.uri = value
        return this
    }

    private fun getString(value: Int): String {
        return context.getString(value)
    }

    inner class Builder(
        private val context: Context,
        private val notificationStyle: NotificationCompat.Style = NotificationCompat.BigTextStyle()
            .bigText(message).setBigContentTitle(title)
    ) {

        fun getNotificationBuilder(): NotificationCompat.Builder {
            val notificationBuilder =
                NotificationCompat.Builder(context, notificationChannelId).setSmallIcon(smallIcon)
                    .setLargeIcon(
                        largeIcon
                    )
                    .setContentTitle(title)
                    .setShowWhen(true)
                    .setContentText(message)
                    .setOngoing(onGoing)
                    .setOnlyAlertOnce(alertOnlyOnce)
                    .setStyle(notificationStyle)
                    .setColor(notificationColor)
                    .setPriority(priority)
                    .setAutoCancel(autoCancel)
                    .setVibrate(vibrationPattern)


            getContentIntent()?.let {
                notificationBuilder.setContentIntent(it)
            }

            getActionIntent()?.let {
                notificationBuilder.addAction(actionIntentIcon, actionText, it)
            }

            return notificationBuilder
        }

        @SuppressLint("MissingPermission")
        fun showNotification(notificationId: Int) {
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, getNotificationBuilder().build())
        }

        @SuppressLint("MissingPermission")
        fun showNotification(notificationId: Int, getViewIntent: String.() -> Intent) {
            var viewPendingIntent: PendingIntent? = null
            uri?.let {
                val intent = getViewIntent(it)
                viewPendingIntent = getViewPendingIntent(intent, context)
            }

            val builder = getNotificationBuilder()

            viewPendingIntent?.let {
                builder.addAction(actionIntentIcon, actionText, it)
            }

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, builder.build())
        }
    }

    companion object {
        fun getIntentFlag(): Int {
            return PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        }
    }

}