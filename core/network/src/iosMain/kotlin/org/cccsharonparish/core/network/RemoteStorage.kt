package org.cccsharonparish.core.network

import cocoapods.FirebaseStorage.FIRStorageTaskStatusFailure
import cocoapods.FirebaseStorage.FIRStorageTaskStatusProgress
import org.cccsharonparish.core.domain.exception.Result
import org.cccsharonparish.core.network.firebase.Path
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.ios
import dev.gitlive.firebase.storage.storage
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class RemoteStorage : IRemoteStorage {
    @OptIn(ExperimentalForeignApi::class)
    private val storage = Firebase.storage.ios

    @OptIn(ExperimentalForeignApi::class)
    actual override suspend fun downloadFileFrom(
        path: Path,
    ): DownloadTaskResult = suspendCoroutine { continuation ->
        val storageRef =
            storage.reference().child("${path.folderName}/${path.fileName}${path.extension}")
        val downloadTo =
            LocalStorage.getTemporaryFilePathFor(
                fileName = path.fileName,
                extension = path.extension
            )
        storageRef.writeToFile(downloadTo!!) { url, error ->
            val downloadTask = if (error == null) {
                val appFileUrl = LocalStorage.getAppFilePathFor(path)
                val isSuccessful = LocalStorage.copyFile(downloadTo, appFileUrl!!)
                if (isSuccessful) {
                    val fileManager = NSFileManager.defaultManager
                    val deleteError: CPointer<ObjCObjectVar<NSError?>>? = null
                    fileManager.removeItemAtURL(downloadTo, deleteError)
                }
                DownloadTaskResult(true)
            } else {
                DownloadTaskResult(
                    false,
                    Exception(error.localizedDescription)
                )
            }
            continuation.resume(downloadTask)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual override fun getDownloadTask(
        path: Path,
    ): Flow<Result<DownloadTask, Exception>> = callbackFlow {
        val storage = Firebase.storage.ios
        val storageRef =
            storage.reference().child("${path.folderName}/${path.fileName}${path.extension}")
        val downloadTo =
            LocalStorage.getTemporaryFilePathFor(
                fileName = path.fileName,
                extension = path.extension
            )
        val downloadTask = storageRef.writeToFile(downloadTo!!) { url, error ->
        }
        downloadTask.observeStatus(FIRStorageTaskStatusProgress) { snapshot ->
            val progress = snapshot?.progress()
            if (progress != null) {
                val completedUnitCount = progress.completedUnitCount.toDouble()
                val totalUnitCount = progress.totalUnitCount.toDouble()
                val progressPercentage = ((completedUnitCount / totalUnitCount) * 100).toInt()
                if (progressPercentage == 100) {
                    val appFileUrl = LocalStorage.getAppFilePathFor(path)
                    val isSuccessful = LocalStorage.copyFile(downloadTo, appFileUrl!!)
                    if (isSuccessful) {
                        val fileManager = NSFileManager.defaultManager
                        val deleteError: CPointer<ObjCObjectVar<NSError?>>? = null
                        fileManager.removeItemAtURL(downloadTo, deleteError)
                    }
                   val result = trySend(Result.Success(DownloadTask(progressPercentage, isSuccessful = true)))
                    if(result.isSuccess){
                        close()
                    }
                }
                if (progressPercentage < 100) {
                    trySend(Result.Loading(DownloadTask(progressPercentage)))
                }
            }
        }

        downloadTask.observeStatus(FIRStorageTaskStatusFailure){
            val exception = Exception(it?.error()?.localizedDescription ?: "Unknown download error")
            trySend(Result.Error(exception))
            close(exception)
        }

        awaitClose {
            downloadTask.cancel()
        }

    }

}