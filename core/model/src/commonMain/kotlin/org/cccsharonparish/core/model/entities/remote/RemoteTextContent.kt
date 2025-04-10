package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.local.BibleVerseContent
import org.cccsharonparish.core.model.entities.local.TextContent

@Serializable
class RemoteTextContent(
    override val topic: String,
    override val message: String,
    override val bibleVerse: IBibleVerseContent,
    override val supplication: String,
    override val reflection: String?
) : ITextContent

fun RemoteTextContent.toTextContent(): TextContent {
    return TextContent().apply {
        topic = this@toTextContent.topic
        message = this@toTextContent.message
        bibleVerse = this@toTextContent.bibleVerse as BibleVerseContent
        supplication = this@toTextContent.supplication
        reflection = this@toTextContent.reflection
    }
}