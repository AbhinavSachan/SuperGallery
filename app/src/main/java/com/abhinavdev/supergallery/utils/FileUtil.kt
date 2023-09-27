package com.abhinavdev.supergallery.utils

import android.app.Activity
import android.app.RecoverableSecurityException
import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.abhinavdev.supergallery.constants.FolderNames
import com.abhinavdev.supergallery.models.ImageModel
import java.io.File
import java.lang.ref.WeakReference
import kotlin.coroutines.coroutineContext

object FileUtil {

    fun getAppSpecificHiddenFolder(activity: WeakReference<Context>): String? {
        val path = activity.get()?.getExternalFilesDir(null) ?: return null
        val finalPath = if (path.exists()) {
            "${path.absolutePath}/${FolderNames.HIDDEN_FOLDER}"
        } else {
            if (path.mkdir()) {
                "${path.absolutePath}/${FolderNames.HIDDEN_FOLDER}"
            } else {
                return null
            }
        }
        val finalFile = File(finalPath)
        return if (finalFile.exists()) {
            finalFile.absolutePath
        } else {
            if (finalFile.mkdir()) {
                finalFile.absolutePath
            } else {
                null
            }
        }
    }

    fun getAppSpecificImageFolder(activity: WeakReference<Context>): String? {
        val path = activity.get()?.getExternalFilesDir(null) ?: return null
        val finalPath = if (path.exists()) {
            "${path.absolutePath}/${FolderNames.OPEN_FOLDER}"
        } else {
            if (path.mkdir()) {
                "${path.absolutePath}/${FolderNames.OPEN_FOLDER}"
            } else {
                return null
            }
        }
        val finalFile = File(finalPath)
        return if (finalFile.exists()) {
            finalFile.absolutePath
        } else {
            if (finalFile.mkdir()) {
                finalFile.absolutePath
            } else {
                null
            }
        }
    }

    /**
     * @param directory directory from one of @[Environment] variables
     */
    fun getExternalDirectoryImageFolder(directory: String): String? {
        val file = Environment.getExternalStoragePublicDirectory(directory) ?: return null
        val path = "${file.absolutePath}/${FolderNames.OPEN_FOLDER}"
        val finalFile = File(path)
        return if (finalFile.exists()) {
            finalFile.absolutePath
        } else {
            if (finalFile.mkdir()) {
                finalFile.absolutePath
            } else {
                null
            }
        }
    }

    /**
     * @param directory directory from one of @[Environment] variables
     */
    fun getExternalDirectoryHiddenFolder(directory: String): String? {
        val file = Environment.getExternalStoragePublicDirectory(directory) ?: return null
        val path = "${file.absolutePath}/${FolderNames.HIDDEN_FOLDER}"
        val finalFile = File(path)
        return if (finalFile.exists()) {
            finalFile.absolutePath
        } else {
            if (finalFile.mkdir()) {
                finalFile.absolutePath
            } else {
                null
            }
        }
    }

    fun deleteFromDevice(
        context: WeakReference<Context>,
        imageModels: List<ImageModel>,
        deleteMusicRequestLauncher: ActivityResultLauncher<IntentSenderRequest>?,
        isHidden: Boolean
    ) {
        if (!isHidden) {
            val contentResolver = context.get()?.contentResolver
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