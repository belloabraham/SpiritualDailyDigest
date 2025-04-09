package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.IBibleVerseContent

class BibleVerseContent : RealmObject, IBibleVerseContent {
    override var reference: String = ""
    override var verses: String = ""
    override var keyVerse: String = ""
}