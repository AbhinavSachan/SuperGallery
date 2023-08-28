package com.abhinavdev.supergallery.utils

import android.app.Activity
import android.os.Environment
import com.abhinavdev.supergallery.constants.FolderNames
import java.io.File
import java.lang.ref.WeakReference

object FileUtil {

    fun getAppSpecificHiddenFolder(activity: WeakReference<Activity>): String? {
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

    fun getAppSpecificImageFolder(activity: WeakReference<Activity>): String? {
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
}