package com.abhinavdev.supergallery.utils

import android.content.Context
import android.util.Log
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.models.VideoModel

object LogUtil {

    fun log(context: Context, msg: String) {
        Log.d("Debug TAG ${context.javaClass.name} ", msg)
    }

    fun String.log() {
        Log.d("Debug TAG ", this)
    }

    fun logImageModel(item: ImageModel?) {
        item?.let {
            val logMessage = """
            id - ${item.id}
            displayName - ${item.displayName}
            data - ${item.data}
            bucketDisplayName - ${item.bucketDisplayName}
            bucketId - ${item.bucketId}
            dateAdded - ${item.dateAdded}
            dateModified - ${item.dateModified}
            dateTaken - ${item.dateTaken}
            height - ${item.height}
            isDownload - ${item.isDownload}
            isDrm - ${item.isDrm}
            isPending - ${item.isPending}
            mimeType - ${item.mimeType}
            orientation - ${item.orientation}
            ownerPackageName - ${item.ownerPackageName}
            relativePath - ${item.relativePath}
            resolution - ${item.resolution}
            size - ${item.size}
            title - ${item.title}
            width - ${item.width}
            year - ${item.year}
            uri - ${item.uri}
        """.trimIndent()

            Log.d("TAG", logMessage)
        }
    }


    fun logVideoModel(item: VideoModel?) {
        item?.let {
            val logMessage = """
            id - ${item.id}
            displayName - ${item.displayName}
            data - ${item.data}
            bucketDisplayName - ${item.bucketDisplayName}
            bucketId - ${item.bucketId}
            dateAdded - ${item.dateAdded}
            dateModified - ${item.dateModified}
            dateTaken - ${item.dateTaken}
            height - ${item.height}
            bitrate - ${item.bitrate}
            duration - ${item.duration}
            isDownload - ${item.isDownload}
            isDrm - ${item.isDrm}
            isPending - ${item.isPending}
            mimeType - ${item.mimeType}
            orientation - ${item.orientation}
            ownerPackageName - ${item.ownerPackageName}
            relativePath - ${item.relativePath}
            resolution - ${item.resolution}
            size - ${item.size}
            title - ${item.title}
            width - ${item.width}
            year - ${item.year}
            uri - ${item.uri}
        """.trimIndent()

            Log.d("TAG", logMessage)
        }
    }

}