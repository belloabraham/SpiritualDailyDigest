package org.cccsharonparish.core.model.entities.remote

interface IContent {
    val language: ILanguage?
    val text: ITextContent?
    val audioUrl: String?
}