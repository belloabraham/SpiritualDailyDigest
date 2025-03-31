package org.cccsharonparish.core.network

import org.cccsharonparish.core.network.firebase.Path
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

object LocalStorage {

    fun getAppFilePathFor(path: Path): NSURL? {
        return getAppFilePathFor(
            folderName = path.folderName,
            fileName = path.fileName,
            extension = path.extension
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    fun getAppFilePathFor(folderName: String, fileName: String, extension: String): NSURL? {
        // Get the path to the app's Documents directory
        val fileManager = NSFileManager.defaultManager
        val urls = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)

        // Ensure we have at least one URL
        val documentsDirectory = urls.first() as? NSURL ?: return null

        // Create folder inside the Documents directory
        val folderURL = documentsDirectory.URLByAppendingPathComponent(folderName)

        // Check if folder exists, create it if necessary
        if (folderURL?.path != null && !fileManager.fileExistsAtPath(folderURL.path!!)) {
            try {
                fileManager.createDirectoryAtURL(
                    folderURL,
                    withIntermediateDirectories = true,
                    attributes = null,
                    error = null
                )
            } catch (e: Exception) {
                println("Error creating folder: $e")
                return null
            }
        }

        val fileURL = folderURL?.URLByAppendingPathComponent("$fileName$extension")
        return fileURL
    }

    @OptIn(ExperimentalForeignApi::class)
    fun getTemporaryFilePathFor(fileName: String, extension: String): NSURL? {
        val fileManager = NSFileManager.defaultManager
        val cachesDirectory = fileManager.URLsForDirectory(
            directory = NSCachesDirectory,
            inDomains = NSUserDomainMask
        ).first() as? NSURL ?: return null
        val folderURL =
            cachesDirectory.URLByAppendingPathComponent(Folder.TEMP)
        if (folderURL?.path != null && !fileManager.fileExistsAtPath(folderURL.path!!)) {
            try {
                fileManager.createDirectoryAtURL(
                    folderURL,
                    withIntermediateDirectories = true,
                    attributes = null,
                    error = null
                )
            } catch (e: Exception) {
                println("Error creating folder: $e")
                return null
            }
        }
        val fileURL = folderURL?.URLByAppendingPathComponent("$fileName$extension")
        return fileURL
    }

    @OptIn(ExperimentalForeignApi::class)
    fun copyFile(fromURL: NSURL, toURL: NSURL): Boolean {
        val fileManager = NSFileManager.defaultManager
        return try {
            // Check if the file already exists at the destination
            if (fileManager.fileExistsAtPath(toURL.path!!)) {
                println("File already exists at destination.")
                false
            } else {
                // Copy the file from source to destination
                fileManager.copyItemAtURL(fromURL, toURL, null)
                println("File successfully copied.")
                true
            }
        } catch (e: Exception) {
            println("Error copying file: $e")
            false
        }
    }
}