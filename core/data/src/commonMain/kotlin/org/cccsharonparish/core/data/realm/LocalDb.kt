package org.cccsharonparish.core.data.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.cccsharonparish.core.model.entities.local.BibleVerseContent
import org.cccsharonparish.core.model.entities.local.Content
import org.cccsharonparish.core.model.entities.local.Favourite
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.NotificationTime
import org.cccsharonparish.core.model.entities.local.Preference
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.local.TextContent

object LocalDb {
    val instance: Realm by lazy {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Preference::class,
                SpiritualDailyDigest::class,
                Language::class,
                Content::class,
                BibleVerseContent::class,
                TextContent::class,
                Favourite::class,
                NotificationTime::class
            )
        ).schemaVersion(1).build()
        Realm.open(config)
    }
}
