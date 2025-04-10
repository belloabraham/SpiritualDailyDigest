package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.remote.IBibleVerseContent

class BibleVerseContent : RealmObject, IBibleVerseContent {
    override var reference: String? = null
    override var verses: String? = null
    override var keyVerse: String? = null
}