package org.cccsharonparish.core.model.entities.local

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class User: RealmObject {
    /**
     * Unique IDs contents
     */
    var favouriteContents: RealmList<String> = realmListOf()
}