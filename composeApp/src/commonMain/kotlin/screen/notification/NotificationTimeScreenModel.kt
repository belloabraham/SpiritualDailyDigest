package screen.notification

import cafe.adriel.voyager.core.model.ScreenModel
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.model.entities.local.NotificationTime

class NotificationTimeScreenModel(private val preferenceRepo: IPreferenceRepo) : ScreenModel {

    suspend fun saveNotificationTime(notificationTime: NotificationTime) {
        preferenceRepo.saveNotificationTime(notificationTime)
    }

}