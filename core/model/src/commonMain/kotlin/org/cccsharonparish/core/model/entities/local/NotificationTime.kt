package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class NotificationTime : RealmObject {
    @PrimaryKey
    var id = 123
    var hour: Int = 0
    var minute: Int = 0
    var isNoon:Boolean = false
}