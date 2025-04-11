package org.cccsharonparish.core.model.entities.uistate

import org.cccsharonparish.core.model.entities.local.Content
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.toLanguageContent

data class ContentUIState(
    val id: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val imagePath: String?,
    val contents: List<Content>
)

fun ContentUIState.toSupportedLanguages(): List<Language> {
    return this.contents.map {
        it.language!!
    }
}

fun ContentUIState.toLanguageContent(languageCode:String): LanguageContent? {
   return this.contents.find {
        it.language?.code == languageCode
    }?.toLanguageContent()
}
