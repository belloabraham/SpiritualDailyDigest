package org.cccsharonparish.core.data.realm

import org.cccsharonparish.core.data.entities.Preference
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class LocalDb {
    lateinit var instance: Realm

    init {
        val config = RealmConfiguration.create(schema = setOf(Preference::class))
        instance = Realm.open(config)
    }
}