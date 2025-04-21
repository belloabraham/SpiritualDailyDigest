package screen.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.cccsharonparish.core.domain.Config
import org.cccsharonparish.core.model.entities.local.NotificationTime
import org.cccsharonparish.spiritualdailydigest.receivers.DailyNotificationReceiver
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.looks_good
import java.util.Calendar


fun setNotificationTime(context: Context, notificationTime: NotificationTime) {
    cancelExistingAlarmIfAny(context)
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, DailyNotificationReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        Config.ALARM_REQUEST_CODE,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR, notificationTime.hour)
        set(Calendar.MINUTE, notificationTime.minute)
        set(Calendar.AM_PM, if (notificationTime.isNoon) Calendar.PM else Calendar.AM)

        // If time is before now, set it for the next day
        if (before(Calendar.getInstance())) {
            add(Calendar.DAY_OF_YEAR, 1)
        }
    }
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        pendingIntent
    )

}


fun cancelExistingAlarmIfAny(context: Context) {
    val intent = Intent(context, DailyNotificationReceiver::class.java)
    val existingPendingIntent = PendingIntent.getBroadcast(
        context,
        Config.ALARM_REQUEST_CODE,
        intent,
        PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
    )
    if (existingPendingIntent != null) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(existingPendingIntent)
        existingPendingIntent.cancel()
    }
}

@Composable
actual fun SetNotificationButton(notificationTime: NotificationTime, lookGoodClick: () -> Unit){
    val context = LocalContext.current
    Button(modifier = Modifier.fillMaxWidth(), onClick = {
        setNotificationTime(context,  notificationTime)
        lookGoodClick()
    }) {
        Text(stringResource(Res.string.looks_good))
    }
}