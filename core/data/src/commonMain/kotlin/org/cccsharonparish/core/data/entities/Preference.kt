package org.cccsharonparish.core.data.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Preference: RealmObject{
    @PrimaryKey
    var _id = 123
    var userExitedOnboarding: Boolean = false
}