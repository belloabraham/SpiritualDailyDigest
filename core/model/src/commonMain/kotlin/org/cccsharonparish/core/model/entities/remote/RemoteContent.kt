package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.IContent

@Serializable
class RemoteContent (
    override val language: RemoteLanguage,
    override val text: RemoteTextContent,
    override val audioUrl: String?
) : IContent