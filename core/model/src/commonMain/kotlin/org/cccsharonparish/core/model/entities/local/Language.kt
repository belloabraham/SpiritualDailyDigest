package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.cccsharonparish.core.model.entities.remote.ILanguage

class Language : RealmObject, ILanguage {
    @PrimaryKey
    override var id : String=""
    override var countryCode: String? = null
    override var code: String? = null
    override var label: String? = null
}