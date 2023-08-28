package com.abhinavdev.supergallery.utils

import android.app.Activity
import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.abhinavdev.supergallery.interfaces.CopyImageCallback
import com.abhinavdev.supergallery.interfaces.MoveImageCallback
import com.abhinavdev.supergallery.models.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.lang.ref.WeakReference

class ImageMover(val activity: WeakReference<Activity>) {
    /**
     * @param folder the exact path of the destination folder
     * @param intentSenderLauncher for delete request
     */
    @Throws(IllegalArgumentException::class)
    suspend fun moveImageToFolder(
        imageModels: List<ImageModel>,
        folder: String,
        isHidden: Boolean,
        intentSenderLauncher: ActivityResultLauncher<IntentSenderRequest>?,
        callback: MoveImageCallback
    ) {
        withContext(Dispatchers.IO) {
            for (image in imageModels) {
                image.data.let { data ->
                    val sourceFile = File(data)
                    if (!sourceFile.exists()) {
                        throw IllegalArgumentException("Source file does not exist")
                    }

                    val destinationFile = image.displayName?.let { File(folder, it) }

                    try {
                        if (destinationFile != null) {
                            sourceFile.copyTo(destinationFile, overwrite = true)
                        }
                    } catch (e: IOException) {
                        println("Error copying file: ${e.message}")
                    }
                }
            }
            deleteFromDevice(imageModels, intentSenderLauncher, isHidden)
            callback.moveCompleted()
        }
    }

    /**
     * @param folder the exact path of the destination folder
     */
    @Throws(IllegalArgumentException::class)
    suspend fun copyImageToFolder(
        imageModels: List<ImageModel>, folder: String, callback: CopyImageCallback
    ) {
        withContext(Dispatchers.IO) {
            for (image in imageModels) {
                image.data.let { data ->
                    val sourceFile = File(data)
                    if (!sourceFile.exists()) {
                        throw IllegalArgumentException("Source file does not exist")
                    }
                    val destinationFile = image.displayName?.let { File(folder, it) }

                    try {
                        if (destinationFile != null) {
                            sourceFile.copyTo(destinationFile, overwrite = true)
                        }
                    } catch (e: IOException) {
                        println("Error copying file: ${e.message}")
                    }
                }
            }
            callback.copyCompleted()
        }
    }

    private fun deleteFromDevice(
        imageModels: List<ImageModel>,
        deleteMusicRequestLauncher: ActivityResultLauncher<IntentSenderRequest>?,
        isHidden: Boolean
    ) {
        if (!isHidden) {
            val contentResolver = activity.get()?.contentResolver
            contentResolver?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val uris = imageModels.map { it.uri }
                    // for android 11 and above
                    // Create a PendingIntent for the delete request
                    val pendingIntent = MediaStore.createDeleteRequest(contentResolver, uris)

                    // Create an IntentSenderRequest for the delete request
                    val request = IntentSenderRequest.Builder(pendingIntent.intentSender).build()

                    // Launch the delete request using the ActivityResultLauncher
                    deleteMusicRequestLauncher?.launch(request)
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                    // for android 10
                    try {
                        deleteItemFromMediaStore(imageModels, contentResolver)
                    } // for android 10 we must catch a recoverable security exception
                    catch (e: RecoverableSecurityException) {
                        val intent = e.userAction.actionIntent.intentSender
                        // Create an IntentSenderRequest for the delete request
                        val request = IntentSenderRequest.Builder(intent).build()
                        // Launch the delete request using the ActivityResultLauncher
                        deleteMusicRequestLauncher?.launch(request)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                } else {
                    // for older devices
                    try {
                        deleteItemFromMediaStore(imageModels, contentResolver)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            imageModels.forEach {
                val sourceFile = File(it.data)
                try {
                    sourceFile.delete()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun deleteItemFromMediaStore(
        imageModels: List<ImageModel>, contentResolver: ContentResolver
    ) {
        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val selectionArgs = imageModels.map {
            it.id.toString()
        }
        contentResolver.delete(
            contentUri, MediaStore.Audio.Media._ID + "=?", selectionArgs.toTypedArray()
        )
    }

}
