package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class SpiritualDailyDigest: RealmObject {
    @PrimaryKey
    var id: String? = null
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var imagePath: String? = null
    var tags: RealmList<String> = realmListOf()
    var contents: RealmList<Content> = realmListOf()
}
