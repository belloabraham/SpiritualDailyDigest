package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Preference : RealmObject {
    @PrimaryKey
    var id = 123
    var fontSize = 20f
    var userExitedOnboarding: Boolean = false
    var selectedContentLanguageCode: String? = null
    var notificationTime: NotificationTime? = null
}