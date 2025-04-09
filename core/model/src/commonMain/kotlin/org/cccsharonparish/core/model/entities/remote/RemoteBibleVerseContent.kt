package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable

@Serializable
class RemoteBibleVerseContent(
    val reference: String,
    val verses: String,
    val keyVerse: String
)