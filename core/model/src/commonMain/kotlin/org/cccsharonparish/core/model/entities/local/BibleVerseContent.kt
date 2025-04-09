package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject

class BibleVerseContent : RealmObject {
    var reference: String? = null
    var verses: String? = null
    var keyVerse: String? = null
}