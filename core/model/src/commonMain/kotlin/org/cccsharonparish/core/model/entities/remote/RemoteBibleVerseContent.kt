package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.IBibleVerseContent

@Serializable
class RemoteBibleVerseContent(
    override val reference: String,
    override val verses: String,
    override val keyVerse: String
) : IBibleVerseContent