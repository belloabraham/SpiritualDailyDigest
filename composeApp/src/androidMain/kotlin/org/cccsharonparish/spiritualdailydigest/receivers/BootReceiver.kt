package org.cccsharonparish.spiritualdailydigest.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
           // scheduleDailyReminder(context, 7, 0) // example: 7:00 AM
        }
    }

}