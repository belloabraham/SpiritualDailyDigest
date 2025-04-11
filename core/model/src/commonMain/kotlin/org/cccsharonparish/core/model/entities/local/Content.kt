package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.remote.IContent
import org.cccsharonparish.core.model.entities.uistate.LanguageContent

class Content : RealmObject, IContent {
    override var language: Language? = Language()
    override var text: TextContent? = TextContent()
    override var audioUrl: String? = null
}

fun Content.toLanguageContent(): LanguageContent {
    return  LanguageContent(
        text = text,
        audioUrl = audioUrl
    )
}