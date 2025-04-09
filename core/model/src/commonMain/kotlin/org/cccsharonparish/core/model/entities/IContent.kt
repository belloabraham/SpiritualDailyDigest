package org.cccsharonparish.core.model.entities

import org.cccsharonparish.core.model.entities.remote.RemoteLanguage
import org.cccsharonparish.core.model.entities.remote.RemoteTextContent

interface IContent {
    val language: ILanguage
    val text: ITextContent
    val audioUrl: String?
}