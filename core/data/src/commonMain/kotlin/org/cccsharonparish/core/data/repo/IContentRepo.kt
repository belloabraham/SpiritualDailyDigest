package org.cccsharonparish.core.data.repo

import kotlinx.coroutines.flow.Flow
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest

interface IContentRepo {
    suspend fun getRemotePublishedContentsForYear(year: Int): Result<List<RemoteSpiritualDailyDigest>, FirestoreError>

    suspend fun getRemotePublishedContentsWhere(
        year: Int,
        startMonth: Int,
        orderBy: String
    ): Result<List<RemoteSpiritualDailyDigest>, FirestoreError>

    suspend fun saveRemotePublishedContents(remoteContents: List<RemoteSpiritualDailyDigest>)
    fun getPublishedContentForId(id: String): SpiritualDailyDigest?
    fun getALiveListOfAllPublishedContent(): Flow<List<SpiritualDailyDigest>>
    fun getLivePublishedContentForId(id: String): Flow<SpiritualDailyDigest?>
    fun getLivePublishedContentForToday(): Flow<SpiritualDailyDigest?>
    fun getPublishedContentForToday(): SpiritualDailyDigest?
    fun getAllPublishedContents(): List<SpiritualDailyDigest>
    fun getContentIdForToday(): String
}