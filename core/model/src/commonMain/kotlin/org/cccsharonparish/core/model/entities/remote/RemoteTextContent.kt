package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.ITextContent

@Serializable
class RemoteTextContent(
    override val topic: String,
    override val message: String,
    override val bibleVerse: RemoteBibleVerseContent,
    override val supplication: String,
    override val reflection: String
) : ITextContent