package org.cccsharonparish.core.data.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.cccsharonparish.core.model.entities.local.Preference

class LocalDb {
    val instance: Realm

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(Preference::class)
        ).schemaVersion(1).build()
        instance = Realm.open(config)
    }
}