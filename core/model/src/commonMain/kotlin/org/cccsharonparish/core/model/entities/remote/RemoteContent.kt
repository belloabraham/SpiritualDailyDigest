package org.cccsharonparish.core.model.entities.remote

import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.local.Content
import org.cccsharonparish.core.model.entities.local.Language

@Serializable
class RemoteContent (
    override val language: RemoteLanguage,
    override val text: RemoteTextContent,
    override val audioUrl: String?
) : IContent

fun RemoteContent.toContent(): Content {
    return Content().apply {
        language = this@toContent.language.toLanguage()
        text = this@toContent.text.toTextContent()
        audioUrl = this@toContent.audioUrl
    }
}

fun RemoteContent.toLanguage(): Language {
    return this.language.toLanguage()
}