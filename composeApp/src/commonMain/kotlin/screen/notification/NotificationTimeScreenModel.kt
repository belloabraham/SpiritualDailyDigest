package screen.notification

import cafe.adriel.voyager.core.model.ScreenModel
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.model.entities.local.NotificationTime

class NotificationTimeScreenModel(private val preferenceRepo: IPreferenceRepo) : ScreenModel {

   suspend  fun setNotificationTime(hour:Int, minute:Int, isNoon:Boolean){
            val notificationTime = NotificationTime().apply {
                    this.hour = hour
                    this.minute = minute
                    this.isNoon = isNoon
            }
            preferenceRepo.setNotificationTime(notificationTime)
    }

}