package org.cccsharonparish.spiritualdailydigest.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.cccsharonparish.core.data.realm.LocalDb
import org.cccsharonparish.core.data.repo.PreferenceRepo
import org.cccsharonparish.core.model.entities.local.NotificationTime

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val preferenceRepo = PreferenceRepo(LocalDb.instance)
            val notificationTime = preferenceRepo.getNotificationTime()
//            if (notificationTime != null) {
//                scheduleDailyReminder(context, notificationTime.hour, notificationTime.minute)
//            }

           // scheduleDailyReminder(context, 7, 0) // example: 7:00 AM
        }
    }

}