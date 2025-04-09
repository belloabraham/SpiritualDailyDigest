package org.cccsharonparish.core.model.entities.remote

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable

@Serializable
class RemoteSpiritualDailyDigest(
    val id: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val imagePath: String?,
    val tags: List<String>,
    val contents: List<RemoteContent>,
    val isPublished: Boolean,
    val isAwaitingApproval: Boolean,
    val createdBy: String,
    val updatedBy: String?,
    val createdAt: Timestamp,
    val updatedAt: Timestamp?
)