package org.cccsharonparish.spiritualdailydigest.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.cccsharonparish.core.data.realm.LocalDb
import org.cccsharonparish.core.data.repo.PreferenceRepo

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val notificationTime = PreferenceRepo(LocalDb.instance).getNotificationTime()

            notificationTime?.let {
//                setNotificationTime(context, it.hour, it.minute)
            }
        }
    }

}