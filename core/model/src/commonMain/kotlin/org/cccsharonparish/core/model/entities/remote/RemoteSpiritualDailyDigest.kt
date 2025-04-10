package org.cccsharonparish.core.model.entities.remote

import dev.gitlive.firebase.firestore.Timestamp
import io.realm.kotlin.ext.toRealmList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest

@Serializable
class RemoteSpiritualDailyDigest(
    override val id: String,
    override val year: Int,
    override val month: Int,
    override val day: Int,
    override val imagePath: String?,
    override val tags: List<String>,
    override val contents: List<RemoteContent>,
    @Transient
    val isPublished: Boolean = false,
    @Transient
    val isAwaitingApproval: Boolean = false,
    @Transient
    val createdBy: String = "",
    @Transient
    val updatedBy: String? = null,
    @Transient
    val createdAt: Timestamp = Timestamp.now(),
    @Transient
    val updatedAt: Timestamp? = null
) : ISpiritualDailyDigest

fun RemoteSpiritualDailyDigest.toSpiritualDailyDigest(): SpiritualDailyDigest {
    return SpiritualDailyDigest().apply {
        id = this@toSpiritualDailyDigest.id
        year = this@toSpiritualDailyDigest.year
        month = this@toSpiritualDailyDigest.month
        day = this@toSpiritualDailyDigest.day
        imagePath = this@toSpiritualDailyDigest.imagePath
        contents = this@toSpiritualDailyDigest.contents.map { it.toContent() }.toRealmList()
    }
}