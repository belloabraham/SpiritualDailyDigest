package org.cccsharonparish.core.data.repo


import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.cccsharonparish.core.data.firestore.Collection
import org.cccsharonparish.core.data.firestore.Firestore
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.remote.RemoteLanguage
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest
import org.cccsharonparish.core.model.entities.remote.toLanguage
import org.cccsharonparish.core.model.entities.remote.toSpiritualDailyDigest

class ContentRepo(
    private val localDb: Realm,
    private val firestore: Firestore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getSupportedLanguages(): Result<List<RemoteLanguage>, FirestoreError> {
        return withContext(dispatcher) {
            firestore.getListOfData<RemoteLanguage>(Collection.LANGUAGES)
        }
    }

    suspend fun saveSupportedLanguages(supportedLanguages: List<RemoteLanguage>) {
        withContext(dispatcher) {
            val languages = supportedLanguages.map { supportedLanguage ->
                supportedLanguage.toLanguage()
            }
            localDb.write {
                languages.forEach {
                    copyToRealm(it, UpdatePolicy.ALL)
                }
            }
        }
    }

    suspend fun getRemotePublishedContent(year: Int): Result<List<RemoteSpiritualDailyDigest>, FirestoreError> {
        return withContext(dispatcher) {
            firestore.getListOfDataWhere<RemoteSpiritualDailyDigest>(
                Collection.PUBLISHED,
                whereFilter = {
                    "year" equalTo "$year"
                },
                limit = 366
            )
        }
    }

    suspend fun saveRemotePublishedContents(remoteContents: List<RemoteSpiritualDailyDigest>) {
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


}