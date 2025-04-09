package org.cccsharonparish.core.model.entities

import org.cccsharonparish.core.model.entities.remote.RemoteBibleVerseContent

interface ITextContent {
    val topic: String
    val message: String
    val bibleVerse: RemoteBibleVerseContent
    val supplication: String
    val reflection: String
}