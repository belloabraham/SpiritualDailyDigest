package org.cccsharonparish.core.network

import kotlinx.coroutines.flow.Flow
import org.cccsharonparish.core.network.firebase.Path

interface IRemoteStorage {
    suspend fun downloadFileFrom(
        path: Path,
    ): DownloadTaskResult

    fun getDownloadTask(
        path: Path,
    ): Flow<Result<DownloadTask, Exception>>
}