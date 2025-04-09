package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject

class Content : RealmObject {
    var language: Language? = Language()
    var text: TextContent? = TextContent()
    var audioUrl: String? = null
}