package com.abhinavdev.supergallery.utils

import android.app.Activity
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
            FileUtil.deleteFromDevice(
                WeakReference(activity.get()),
                imageModels,
                intentSenderLauncher,
                isHidden
            )
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


}
