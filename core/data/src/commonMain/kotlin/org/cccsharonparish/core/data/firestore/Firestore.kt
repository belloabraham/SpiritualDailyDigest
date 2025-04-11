package org.cccsharonparish.core.data.firestore

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.Filter
import dev.gitlive.firebase.firestore.FilterBuilder
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.FirestoreExceptionCode
import dev.gitlive.firebase.firestore.code
import dev.gitlive.firebase.firestore.firestore
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.domain.logging.Log


class Firestore {
    val db = Firebase.firestore

    suspend fun <T> addData(
        collection: String,
        document: String,
        data: HashMap<String, T>,
        merge: Boolean = true
    ): Result<Unit, FirestoreError> {
        return try {
            val result = db.collection(collection)
                .document(document)
                .set(data, merge){}
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend fun <T> updateData(
        collection: String,
        document: String,
        data: Pair<String, T>
    ): Result<Unit, FirestoreError> {
        return try {
            val result = db.collection(collection).document(document).update(data) {}
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend inline fun <reified T : Any> getData(
        collection: String,
        document: String,
    ): Result<T, FirestoreError> {
        return try {
            val result = db.collection(collection)
                .document(document).get().data<T> {}
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend inline fun <reified T : Any> getData(
        collection: String,
        document: String,
        subCollection: String,
        subDocument: String
    ): Result<T, FirestoreError> {
        return try {
            val result = db.collection(collection).document(document).collection(subCollection)
                .document(subDocument).get().data<T>()
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend inline fun <reified T : Any> getListOfDataWhere(
        collection: String,
        noinline whereFilter: FilterBuilder.() -> Filter?,
        orderBy: String?,
        startAt: Any?,
        startAfter: String?,
        direction: Direction,
        limit: Int?,
        ): Result<List<T>, FirestoreError> {
        var query = db.collection(collection).where(whereFilter)
        orderBy?.let {
            query = query.orderBy(it, direction)
        }
        startAt?.let {
            query = query.startAt(it)
        }
        startAfter?.let {
            query = query.startAfter(it)
        }
        limit?.let {
            query = query.limit(it)
        }
        return try {
            val documents = query.get().documents
            documentsToTypes<T>(documents)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }


    suspend inline fun <reified T : Any> getListOfDataWhere(
        collection: String,
        noinline whereFilter: FilterBuilder.() -> Filter?,
        limit: Int?,
    ): Result<List<T>, FirestoreError> {
        var query = db.collection(collection).where(whereFilter)
        limit?.let {
            query = query.limit(it)
        }
        return try {
            val documents = query.get().documents
            documentsToTypes<T>(documents)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend fun addListOfData(
        collection: String,
        document: String,
        subCollection: String,
        list: List<Any>,
        merge: Boolean = true
    ): Result<Unit, FirestoreError> {
        val batch = db.batch()
        for (item in list) {
            val docRef =
                db.collection(collection).document(document).collection(subCollection).document
            batch.set(docRef, item, merge) {}
        }
        return try {
            val result = batch.commit()
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend fun addListOfData(
        collection: String,
        list: List<Any>,
        merge: Boolean = true
    ): Result<Unit, FirestoreError> {
        val batch = db.batch()
        for (item in list) {
            val docRef = db.collection(collection).document
            batch.set(docRef, item, merge) {}
        }
        return try {
            val result = batch.commit()
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend inline fun <reified T : Any> getListOfData(
        collection: String,
    ): Result<List<T>, FirestoreError> {
        return try {
            val documents = db.collection(collection)
                .get().documents
            documentsToTypes<T>(documents)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend inline fun <reified T : Any> getListOfData(
        collection: String,
        document: String,
        subCollection: String
    ): Result<List<T>, FirestoreError> {
        return try {
            val documents = db.collection(collection).document(document).collection(subCollection)
                .get().documents
            documentsToTypes<T>(documents)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    suspend fun deleteListOfData(
        docIds: List<String>,
        collection: String,
        document: String,
        subCollection: String
    ): Result<Unit, FirestoreError> {
        val batch = db.batch()
        for (id in docIds) {
            val docRef =
                db.collection(collection).document(document).collection(subCollection).document(id)
            batch.delete(docRef)
        }
        return try {
            val result = batch.commit()
            Result.Success(result)
        } catch (e: FirebaseFirestoreException) {
            Result.Error(errorType(e))
        }
    }

    inline fun <reified T : Any> documentsToTypes(documents: List<DocumentSnapshot>): Result<List<T>, FirestoreError> {
        val result = emptyList<T>()
        documents.forEach {
            if (it.exists) {
                result.plus(it.data<T>())
            }
        }
        return Result.Success(result)
    }

}

fun errorType(e: FirebaseFirestoreException): FirestoreError {
    Log.error(e) {
        e.message ?: ""
    }
    return when (e.code) {
        FirestoreExceptionCode.DATA_LOSS -> FirestoreError.DATA_LOSS
        FirestoreExceptionCode.ABORTED -> FirestoreError.ABORTED
        FirestoreExceptionCode.UNKNOWN -> FirestoreError.UNKNOWN
        FirestoreExceptionCode.INTERNAL -> FirestoreError.INTERNAL
        FirestoreExceptionCode.DEADLINE_EXCEEDED -> FirestoreError.DEADLINE_EXCEEDED
        FirestoreExceptionCode.OUT_OF_RANGE -> FirestoreError.OUT_OF_RANGE
        FirestoreExceptionCode.PERMISSION_DENIED -> FirestoreError.PERMISSION_DENIED
        FirestoreExceptionCode.UNIMPLEMENTED -> FirestoreError.UNIMPLEMENTED
        FirestoreExceptionCode.RESOURCE_EXHAUSTED -> FirestoreError.RESOURCE_EXHAUSTED
        FirestoreExceptionCode.INVALID_ARGUMENT -> FirestoreError.INVALID_ARGUMENT
        FirestoreExceptionCode.CANCELLED -> FirestoreError.CANCELLED
        FirestoreExceptionCode.UNAUTHENTICATED -> FirestoreError.UNAUTHENTICATED
        FirestoreExceptionCode.UNAVAILABLE -> FirestoreError.UNAVAILABLE
        FirestoreExceptionCode.ALREADY_EXISTS -> FirestoreError.ALREADY_EXISTS
        FirestoreExceptionCode.FAILED_PRECONDITION -> FirestoreError.FAILED_PRECONDITION
        FirestoreExceptionCode.NOT_FOUND -> FirestoreError.NOT_FOUND
        else -> FirestoreError.OK
    }
}