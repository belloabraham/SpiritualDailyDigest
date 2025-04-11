package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.cccsharonparish.core.model.entities.remote.ISpiritualDailyDigest
import org.cccsharonparish.core.model.entities.uistate.ContentUIState

class SpiritualDailyDigest : RealmObject, ISpiritualDailyDigest {
    @PrimaryKey
    override var id: String = ""
    override var year: Int = 0
    override var month: Int = 0
    override var day: Int = 0
    override var imagePath: String? = null
    override var tags: RealmList<String> = realmListOf()
    override var contents: RealmList<Content> = realmListOf()
}

fun SpiritualDailyDigest.toContentUIState(): ContentUIState {
    return ContentUIState(
        id = this.id,
        month = this.month,
        year = this.year,
        day = this.day,
        imagePath = this.imagePath,
        contents = this.contents
    )
}