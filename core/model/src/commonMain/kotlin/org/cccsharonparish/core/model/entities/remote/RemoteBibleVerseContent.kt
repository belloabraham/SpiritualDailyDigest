package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.local.BibleVerseContent

@Serializable
class RemoteBibleVerseContent(
    override val reference: String,
    override val verses: String,
    override val keyVerse: String
) : IBibleVerseContent

fun RemoteBibleVerseContent.toBibleVerseContent(): BibleVerseContent {
    return BibleVerseContent().apply {
        reference = this@toBibleVerseContent.reference
        verses = this@toBibleVerseContent.verses
        keyVerse = this@toBibleVerseContent.keyVerse
    }
}