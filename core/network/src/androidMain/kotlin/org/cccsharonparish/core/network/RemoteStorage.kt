package org.cccsharonparish.core.network

import org.cccsharonparish.core.domain.logging.Log
import org.cccsharonparish.core.network.firebase.Path
import org.cccsharonparish.core.domain.exception.Result
import com.google.firebase.storage.FileDownloadTask
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.android
import dev.gitlive.firebase.storage.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


actual class RemoteStorage: IRemoteStorage {
    private val storage = Firebase.storage.android

    actual override suspend fun downloadFileFrom(
        path: Path,
    ): DownloadTaskResult {
        val downloadTo = LocalStorage.getTemporaryFilePathFor(
            fileName = path.fileName,
            extension = path.extension
        )
        val storageRef =
            storage.reference.child("${path.folderName}/${path.fileName}${path.extension}")
        return try {
            storageRef.getFile(downloadTo).await()
            val appFilePath = LocalStorage.getAppFilePathFor(path)
            downloadTo.copyTo(appFilePath, true).apply {
                downloadTo.delete()
            }
            DownloadTaskResult(true, null)
        } catch (e: Exception) {
            e.message?.let {
                Log.error(it, e)
            }
            DownloadTaskResult(false, e)
        }

    }

    actual override fun getDownloadTask(
        path: Path,
    ):Flow<Result<DownloadTask, Exception>> = callbackFlow {
        val downloadTo = LocalStorage.getTemporaryFilePathFor(
            fileName = path.fileName,
            extension = path.extension
        )
        val storageRef =
            storage.reference.child("${path.folderName}/${path.fileName}${path.extension}")
        val downloadTask = storageRef.getFile(downloadTo)
        val listener: (FileDownloadTask.TaskSnapshot) -> Unit = { taskSnapshot ->
            val progress =
                (100 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount).toInt()
            if (progress == 100) {
                val appFilePath = LocalStorage.getAppFilePathFor(path)
                downloadTo.copyTo(appFilePath, true).apply {
                    downloadTo.delete()
                }
                trySend(Result.Success(DownloadTask(progress, isSuccessful = true)))
            }
            if (progress < 100) {
                trySend(Result.Loading(DownloadTask(progress)))
            }
        }

        downloadTask.addOnProgressListener(listener)

        downloadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                close()
            } else {
                val exception = task.exception ?: Exception("Unknown download error")
                trySend(Result.Error(exception))
                close(exception)
            }
        }

        awaitClose {
            downloadTask.removeOnProgressListener(listener)
            downloadTask.cancel()
        }
    }
}