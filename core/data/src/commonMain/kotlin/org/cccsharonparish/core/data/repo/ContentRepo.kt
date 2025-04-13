package org.cccsharonparish.core.data.repo


import dev.gitlive.firebase.firestore.Direction
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.cccsharonparish.core.common.utils.DateTimeUtil
import org.cccsharonparish.core.data.firestore.Collection
import org.cccsharonparish.core.data.firestore.Firestore
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.local.Favourite
import org.cccsharonparish.core.model.entities.local.Preference
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest
import org.cccsharonparish.core.model.entities.remote.toSpiritualDailyDigest

class ContentRepo(
    private val localDb: Realm,
    private val firestore: Firestore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContentRepo {

    override suspend fun getRemotePublishedContentsForYear(year: Int): Result<List<RemoteSpiritualDailyDigest>, FirestoreError> {
        return withContext(dispatcher) {
            firestore.getListOfDataWhere<RemoteSpiritualDailyDigest>(
                Collection.PUBLISHED,
                whereFilter = {
                    "year" equalTo "$year"
                },
                limit = null
            )
        }
    }

    override suspend fun getRemotePublishedContentsWhere(
        year: Int,
        startMonth: Int,
        orderBy: String
    ): Result<List<RemoteSpiritualDailyDigest>, FirestoreError> {
        return withContext(dispatcher) {
            firestore.getListOfDataWhere<RemoteSpiritualDailyDigest>(
                Collection.PUBLISHED,
                whereFilter = {
                    "year" equalTo "$year"
                },
                orderBy = orderBy,
                startAt = startMonth,
                startAfter = null,
                direction = Direction.ASCENDING,
                limit = null,
            )
        }
    }

    override suspend fun saveRemotePublishedContents(remoteContents: List<RemoteSpiritualDailyDigest>) {
        withContext(dispatcher) {
            val contents = remoteContents.map { remoteContent ->
                remoteContent.toSpiritualDailyDigest()
            }
            localDb.write {
                contents.forEach {
                    copyToRealm(it, UpdatePolicy.ALL)
                }
            }
        }
    }


    override fun getPublishedContentForId(id: String): SpiritualDailyDigest? {
        return try {
            localDb.query<SpiritualDailyDigest>("id == $0", id).find().first()
        } catch (e: Exception) {
            null
        }
    }

    override fun getALiveListOfAllPublishedContent(): Flow<List<SpiritualDailyDigest>> {
        return localDb
            .query<SpiritualDailyDigest>()
            .asFlow()
            .map { results ->
                results.list
            }
    }

    override fun getLivePublishedContentForId(id: String): Flow<SpiritualDailyDigest?> {
        return localDb
            .query<SpiritualDailyDigest>("id == $0", id)
            .asFlow()
            .map { results ->
                results.list.firstOrNull()
            }
    }

    override fun getLivePublishedContentForToday(): Flow<SpiritualDailyDigest?> {
        val id = getContentIdForToday()
        return getLivePublishedContentForId(id)
    }

    override fun getPublishedContentForToday(): SpiritualDailyDigest? {
        val id = getContentIdForToday()
        return getPublishedContentForId(id)
    }

    override fun getAllPublishedContents(): List<SpiritualDailyDigest> {
        return try {
            localDb.query<SpiritualDailyDigest>().find()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getContentIdForToday(): String {
        val localDate = DateTimeUtil.date()
        return "${localDate.dayOfMonth}-${localDate.monthNumber}-${localDate.year}"
    }

    override fun getFavouriteById(id: String): Favourite? {
        return try {
            localDb.query<Favourite>("id == $0", id).find().first()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun removeFromFavouriteById(id: String) {
        withContext(dispatcher) {
            localDb.write {
                try {
                    val item = query<Favourite>("id == $0", id).find().first()
                    delete(item)
                } catch (_: Exception) {
                }
            }
        }
    }

    override suspend fun addToFavourite(value: Favourite) {
        withContext(dispatcher) {
            localDb.write {
                copyToRealm(value, UpdatePolicy.ALL)
            }
        }
    }
}