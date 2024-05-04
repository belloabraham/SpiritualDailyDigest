package org.cccsharonparish.core.data.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Preference: RealmObject{
    @PrimaryKey
    var _id = 123
    var languageIndex = 0
    var fontSize = 20f
    var userExitedOnboarding: Boolean = false
}