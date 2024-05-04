package org.cccsharonparish.core.data.realm

import org.cccsharonparish.core.data.entities.Preference
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class LocalDb {
    val instance: Realm

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(Preference::class)
        ).schemaVersion(1).build()
        instance = Realm.open(config)
    }
}