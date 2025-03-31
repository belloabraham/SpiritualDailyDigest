package org.cccsharonparish.core.network

import org.cccsharonparish.core.domain.applicationContext
import org.cccsharonparish.core.network.firebase.Path
import java.io.File

object LocalStorage {

    fun getTemporaryFilePathFor(fileName: String, extension: String): File {
        val folder = File(applicationContext.cacheDir, Folder.TONIC_SOLFA)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return File(folder, "$fileName$extension")
    }

    fun getAppFilePathFor(
        path: Path,
    ): File {
        return getAppFilePathFor(
            folderName = path.folderName,
            fileName = path.fileName,
            extension = path.extension,
        )
    }

    fun getAppFilePathFor(
        folderName: String,
        fileName: String,
        extension: String,
    ): File {
        val folder = File(applicationContext.filesDir, folderName)
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return File(folder, "$fileName$extension")
    }
}