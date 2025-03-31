package org.cccsharonparish.core.network

import kotlinx.coroutines.flow.Flow
import org.cccsharonparish.core.network.firebase.Path
import org.cccsharonparish.core.domain.exception.Result


expect class RemoteStorage() : IRemoteStorage {
    override suspend fun downloadFileFrom(path: Path): DownloadTaskResult

    override fun getDownloadTask(path: Path): Flow<Result<DownloadTask, Exception>>
}