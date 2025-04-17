package org.cccsharonparish.spiritualdailydigest.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import domain.getContentIdForToday
import org.cccsharonparish.core.common.helpers.notification.NotificationBuilder
import org.cccsharonparish.core.common.utils.Locale
import org.cccsharonparish.core.data.realm.LocalDb
import org.cccsharonparish.core.data.repo.ContentRepo
import org.cccsharonparish.core.data.repo.PreferenceRepo
import org.cccsharonparish.core.domain.Config
import org.cccsharonparish.core.model.entities.local.Content
import org.cccsharonparish.spiritualdailydigest.MainActivity

class DailyNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val contentIdForToday = getContentIdForToday()
        val contentRepo = ContentRepo(LocalDb.instance)
        val contentForToday = contentRepo.getPublishedContentForId(contentIdForToday)

        contentForToday?.let { content ->
            val preferenceRepo = PreferenceRepo(LocalDb.instance)
            val languageCode =
                preferenceRepo.getSelectedContentLanguageCode() ?: Locale.getAppLanguageCode()

            var languageContent = getContentByLanguage(languageCode, content.contents)

            if (languageContent == null) {
                languageContent =
                    getContentByLanguage(Config.DEFAULT_CONTENT_LANGUAGE_CODE, content.contents)
            }

            if (languageContent != null) {
                val activityIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    activityIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                val notificationBuilder = NotificationBuilder(context)
                val textContent = languageContent.text!!
                notificationBuilder.setTitle(textContent.topic!!)
                notificationBuilder.setMessage(textContent.message!!)
                notificationBuilder.setContentIntent(pendingIntent)
                notificationBuilder.Builder(context).showNotification(Config.DAILY_NOTIFICATION_ID)
            }

        }
    }
}

fun getContentByLanguage(languageCode: String, contents: List<Content>): Content? {
    return contents.find {
        it.language?.code == languageCode
    }
}