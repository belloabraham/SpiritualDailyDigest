package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.ITextContent

class TextContent : RealmObject, ITextContent {
    override var topic: String = ""
    override var message: String = ""
    override var bibleVerse: BibleVerseContent = BibleVerseContent()
    override var supplication: String = ""
    override var reflection: String = ""
}