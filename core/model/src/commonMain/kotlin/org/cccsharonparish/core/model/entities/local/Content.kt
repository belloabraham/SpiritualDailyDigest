package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.IContent
import org.cccsharonparish.core.model.entities.remote.RemoteBibleVerseContent
import org.cccsharonparish.core.model.entities.remote.RemoteLanguage
import org.cccsharonparish.core.model.entities.remote.RemoteTextContent

class Content : RealmObject, IContent {
    override var language: RemoteLanguage = RemoteLanguage("","","")
    override var text: RemoteTextContent = RemoteTextContent("","", RemoteBibleVerseContent("","",""),"","")
    override var audioUrl: String? = null
}