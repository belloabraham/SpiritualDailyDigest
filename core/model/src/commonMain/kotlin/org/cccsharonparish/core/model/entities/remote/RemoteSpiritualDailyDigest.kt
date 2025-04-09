package org.cccsharonparish.core.model.entities.remote

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable
import org.cccsharonparish.core.model.entities.ISpiritualDailyDigest

@Serializable
class RemoteSpiritualDailyDigest(
    override val id: String,
    override val year: Int,
    override val month: Int,
    override val day: Int,
    override val imagePath: String?,
    override val tags: List<String>,
    override val contents: List<RemoteContent>,
    val isPublished: Boolean,
    val isAwaitingApproval: Boolean,
    val createdBy: String,
    val updatedBy: String?,
    val createdAt: Timestamp,
    val updatedAt: Timestamp?
) : ISpiritualDailyDigest