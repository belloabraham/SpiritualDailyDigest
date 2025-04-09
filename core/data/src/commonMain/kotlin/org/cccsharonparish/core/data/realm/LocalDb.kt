package org.cccsharonparish.core.data.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.Preference
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest

class LocalDb {
    val instance: Realm

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(Preference::class, SpiritualDailyDigest::class, Language::class)
        ).schemaVersion(1).build()
        instance = Realm.open(config)
    }
}