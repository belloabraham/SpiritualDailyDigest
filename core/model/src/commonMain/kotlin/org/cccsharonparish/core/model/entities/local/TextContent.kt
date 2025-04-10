package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.remote.ITextContent

class TextContent : RealmObject, ITextContent {
    override var topic: String? = null
    override var message: String? = null
    override var bibleVerse: BibleVerseContent? = BibleVerseContent()
    override var supplication: String? = null
    override var reflection: String? = null
}