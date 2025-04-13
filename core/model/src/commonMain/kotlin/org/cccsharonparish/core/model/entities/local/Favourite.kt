package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Favourite : RealmObject {
    @PrimaryKey
    var id: String = ""
    var topic: String? = null
}