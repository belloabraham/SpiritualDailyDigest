package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.types.RealmObject

class Language : RealmObject {
    var countryCode: String? = null
    var code: String? = null
    var label: String? = null
}