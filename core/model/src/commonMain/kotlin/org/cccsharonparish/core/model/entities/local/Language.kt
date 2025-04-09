package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import org.cccsharonparish.core.model.entities.ILanguage

class Language : RealmObject, ILanguage {
    override var countryCode: String = ""
    override var code: String = ""
    override var label: String = ""
}