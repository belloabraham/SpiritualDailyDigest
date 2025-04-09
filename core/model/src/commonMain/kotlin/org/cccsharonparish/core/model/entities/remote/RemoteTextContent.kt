package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable

@Serializable
class RemoteTextContent(
    val topic: String,
    val message: String,
    val bibleVerse: RemoteBibleVerseContent,
    val supplication: String,
    val reflection: String
)