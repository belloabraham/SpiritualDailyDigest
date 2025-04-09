package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject

class TextContent : RealmObject {
    var topic: String? = null
    var message: String? = null
    var bibleVerse: BibleVerseContent? = BibleVerseContent()
    var supplication: String? = null
    var reflection: String? = null
}