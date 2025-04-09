package org.cccsharonparish.core.data.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.cccsharonparish.core.model.entities.local.BibleVerseContent
import org.cccsharonparish.core.model.entities.local.Content
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.Preference
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.local.TextContent

class LocalDb {
    val instance: Realm

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Preference::class,
                SpiritualDailyDigest::class,
                Language::class,
                Content::class,
                BibleVerseContent::class,
                TextContent::class
            )
        ).schemaVersion(1).build()
        instance = Realm.open(config)
    }
}