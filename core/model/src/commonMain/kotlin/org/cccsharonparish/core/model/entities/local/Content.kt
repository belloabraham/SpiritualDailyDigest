package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.IContent

class Content : RealmObject, IContent {
    override var language: Language = Language()
    override var text: TextContent = TextContent()
    override var audioUrl: String? = null
}