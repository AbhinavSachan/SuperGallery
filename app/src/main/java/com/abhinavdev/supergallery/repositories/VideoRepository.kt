package com.abhinavdev.supergallery.repositories

import android.app.Activity
import android.content.ContentUris
import android.provider.MediaStore
import android.util.Log
import com.abhinavdev.supergallery.models.VideoModel
import com.abhinavdev.supergallery.utils.FileUtil
import java.io.File
import java.lang.ref.WeakReference

class VideoRepository(private val activity: WeakReference<Activity>) {

    fun fetchAllVideos(): MutableList<VideoModel> {
        val videoModels = mutableListOf<VideoModel>()
        val contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            // Add other fields as needed
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.HEIGHT,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.BITRATE,
            MediaStore.Video.Media.IS_DOWNLOAD,
            MediaStore.Video.Media.IS_DRM,
            MediaStore.Video.Media.IS_PENDING,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.ORIENTATION,
            MediaStore.Video.Media.OWNER_PACKAGE_NAME,
            MediaStore.Video.Media.RELATIVE_PATH,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.YEAR
        )

        val cursor =
            activity.get()?.contentResolver?.query(contentUri, projection, null, null, null)


        cursor?.use {
            // Retrieve column indices
            val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val displayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
            val dateAddedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val dateModifiedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
            val dateTakenColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
            val heightColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)
            val durationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val bitrateColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BITRATE)
            val isDownloadColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_DOWNLOAD)
            val isDrmColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_DRM)
            val isPendingColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_PENDING)
            val mimeTypeColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val orientationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ORIENTATION)
            val ownerPackageNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.OWNER_PACKAGE_NAME)
            val relativePathColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RELATIVE_PATH)
            val resolutionColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)
            val sizeColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val titleColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
            val widthColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
            val yearColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.YEAR)

            while (it.moveToNext()) {
                val id = cursor.getLong(idColumnIndex)
                val displayName = cursor.getString(displayNameColumnIndex)
                val data = cursor.getString(dataColumnIndex)
                val bucketDisplayName = cursor.getString(bucketDisplayNameColumnIndex)
                val bucketId = cursor.getString(bucketIdColumnIndex)
                val dateAdded = cursor.getLong(dateAddedColumnIndex)
                val dateModified = cursor.getLong(dateModifiedColumnIndex)
                val dateTaken = cursor.getLong(dateTakenColumnIndex)
                val height = cursor.getInt(heightColumnIndex)
                val duration = cursor.getLong(durationColumnIndex)
                val bitrate = cursor.getInt(bitrateColumnIndex)
                val isDownload = cursor.getInt(isDownloadColumnIndex)
                val isDrm = cursor.getInt(isDrmColumnIndex)
                val isPending = cursor.getInt(isPendingColumnIndex)
                val mimeType = cursor.getString(mimeTypeColumnIndex)
                val orientation = cursor.getInt(orientationColumnIndex)
                val ownerPackageName = cursor.getString(ownerPackageNameColumnIndex)
                val relativePath = cursor.getString(relativePathColumnIndex)
                val resolution = cursor.getString(resolutionColumnIndex)
                val size = cursor.getLong(sizeColumnIndex)
                val title = cursor.getString(titleColumnIndex)
                val width = cursor.getInt(widthColumnIndex)
                val year = cursor.getInt(yearColumnIndex)
                val videoUri = ContentUris.withAppendedId(contentUri, id)
                val videoModel = VideoModel(
                    id = id,
                    displayName = displayName,
                    data = data,
                    bucketDisplayName = bucketDisplayName,
                    bucketId = bucketId,
                    dateAdded = dateAdded,
                    dateModified = dateModified,
                    dateTaken = dateTaken,
                    height = height,
                    duration = duration,
                    bitrate = bitrate,
                    isDownload = isDownload,
                    isDrm = isDrm,
                    isPending = isPending,
                    mimeType = mimeType,
                    orientation = orientation,
                    ownerPackageName = ownerPackageName,
                    relativePath = relativePath,
                    resolution = resolution,
                    size = size,
                    title = title,
                    width = width,
                    year = year,
                    uri = videoUri
                )

                videoModels.add(videoModel)
            }
        }

        Log.d("TAG", "${videoModels.size}")
        videoModels.map {
            Log.d("TAG", "${it.data}")
        }

        return videoModels
    }

    fun fetchFromFolder(exactFolderName: String): MutableList<VideoModel> {
        val videoModels = mutableListOf<VideoModel>()
        val contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            // Add other fields as needed
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.HEIGHT,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.BITRATE,
            MediaStore.Video.Media.IS_DOWNLOAD,
            MediaStore.Video.Media.IS_DRM,
            MediaStore.Video.Media.IS_PENDING,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.ORIENTATION,
            MediaStore.Video.Media.OWNER_PACKAGE_NAME,
            MediaStore.Video.Media.RELATIVE_PATH,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.YEAR
        )

        val selection = "${MediaStore.Video.Media.DATA} LIKE ?"
        val selectionArgs = arrayOf("%/$exactFolderName/%")

        val cursor = activity.get()?.contentResolver?.query(
            contentUri, projection, selection, selectionArgs, null
        )

        cursor?.use {
            // Retrieve column indices
            val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val displayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
            val dateAddedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
            val dateModifiedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
            val dateTakenColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
            val heightColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)
            val durationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val bitrateColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BITRATE)
            val isDownloadColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_DOWNLOAD)
            val isDrmColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_DRM)
            val isPendingColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.IS_PENDING)
            val mimeTypeColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
            val orientationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ORIENTATION)
            val ownerPackageNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.OWNER_PACKAGE_NAME)
            val relativePathColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RELATIVE_PATH)
            val resolutionColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)
            val sizeColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val titleColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
            val widthColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
            val yearColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.YEAR)

            while (it.moveToNext()) {
                val id = cursor.getLong(idColumnIndex)
                val displayName = cursor.getString(displayNameColumnIndex)
                val data = cursor.getString(dataColumnIndex)
                val bucketDisplayName = cursor.getString(bucketDisplayNameColumnIndex)
                val bucketId = cursor.getString(bucketIdColumnIndex)
                val dateAdded = cursor.getLong(dateAddedColumnIndex)
                val dateModified = cursor.getLong(dateModifiedColumnIndex)
                val dateTaken = cursor.getLong(dateTakenColumnIndex)
                val height = cursor.getInt(heightColumnIndex)
                val duration = cursor.getLong(durationColumnIndex)
                val bitrate = cursor.getInt(bitrateColumnIndex)
                val isDownload = cursor.getInt(isDownloadColumnIndex)
                val isDrm = cursor.getInt(isDrmColumnIndex)
                val isPending = cursor.getInt(isPendingColumnIndex)
                val mimeType = cursor.getString(mimeTypeColumnIndex)
                val orientation = cursor.getInt(orientationColumnIndex)
                val ownerPackageName = cursor.getString(ownerPackageNameColumnIndex)
                val relativePath = cursor.getString(relativePathColumnIndex)
                val resolution = cursor.getString(resolutionColumnIndex)
                val size = cursor.getLong(sizeColumnIndex)
                val title = cursor.getString(titleColumnIndex)
                val width = cursor.getInt(widthColumnIndex)
                val year = cursor.getInt(yearColumnIndex)
                val videoUri = ContentUris.withAppendedId(contentUri, id)
                val videoModel = VideoModel(
                    id = id,
                    displayName = displayName,
                    data = data,
                    bucketDisplayName = bucketDisplayName,
                    bucketId = bucketId,
                    dateAdded = dateAdded,
                    dateModified = dateModified,
                    dateTaken = dateTaken,
                    height = height,
                    duration = duration,
                    bitrate = bitrate,
                    isDownload = isDownload,
                    isDrm = isDrm,
                    isPending = isPending,
                    mimeType = mimeType,
                    orientation = orientation,
                    ownerPackageName = ownerPackageName,
                    relativePath = relativePath,
                    resolution = resolution,
                    size = size,
                    title = title,
                    width = width,
                    year = year,
                    uri = videoUri
                )

                videoModels.add(videoModel)
            }
        }

        Log.d("TAG", "${videoModels.size}")
        videoModels.map {
            Log.d("TAG", "${it.data}")
        }

        return videoModels
    }

    fun fetchFromDefaultHiddenFolder(): MutableList<VideoModel> {
        val videoModels = mutableListOf<VideoModel>()
        var pos = 0
        val videoExtensions = listOf("mp4", "avi", "mov", "mkv", "wmv", "flv", "webm")
        val pathname = FileUtil.getAppSpecificHiddenFolder(activity)
        val allFiles = pathname?.let {
            val file = File(it)
            if (!file.exists()) return videoModels
            file.listFiles { _, name ->
                val extension = name.substringAfterLast(".", "")
                extension.lowercase() in videoExtensions
            }
        }
        allFiles?.map {
            val videoModel = VideoModel(
                id = pos.toLong(), displayName = it.name, data = it.absolutePath
            )
            pos += 1
            videoModels.add(videoModel)
        }
        return videoModels
    }

    fun fetchFromOtherHiddenFolder(absFilePath: String): MutableList<VideoModel> {
        val videoModels = mutableListOf<VideoModel>()
        var pos = 0
        val videoExtensions = listOf("mp4", "avi", "mov", "mkv", "wmv", "flv", "webm")
        val file = File(absFilePath)
        if (!file.exists()) return videoModels
        val allFiles = file.listFiles { _, name ->
            val extension = name.substringAfterLast(".", "")
            extension.lowercase() in videoExtensions
        }
        allFiles?.map {
            val videoModel = VideoModel(
                id = pos.toLong(), displayName = it.name, data = it.absolutePath
            )
            pos += 1
            videoModels.add(videoModel)
        }
        return videoModels
    }

}