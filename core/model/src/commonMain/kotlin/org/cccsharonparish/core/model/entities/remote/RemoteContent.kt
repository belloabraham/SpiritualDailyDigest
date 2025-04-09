package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable

@Serializable
class RemoteContent (
    val language: RemoteLanguage,
    val text: RemoteTextContent,
    val audioUrl: String?
)