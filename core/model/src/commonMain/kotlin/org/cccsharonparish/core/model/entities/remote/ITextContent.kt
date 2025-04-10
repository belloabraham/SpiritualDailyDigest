package org.cccsharonparish.core.model.entities.remote

interface ITextContent {
    val topic: String?
    val message: String?
    val bibleVerse: IBibleVerseContent?
    val supplication: String?
    val reflection: String?
}