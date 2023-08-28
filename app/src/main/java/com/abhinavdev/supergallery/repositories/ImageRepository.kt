package com.abhinavdev.supergallery.repositories

import android.app.Activity
import android.content.ContentUris
import android.provider.MediaStore
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.utils.FileUtil
import java.io.File
import java.lang.ref.WeakReference

class ImageRepository(private val activity: WeakReference<Activity>) {
    fun fetchAllImages(): MutableList<ImageModel> {
        val imageList = mutableListOf<ImageModel>()
        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            // Add other fields as needed
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.IS_DOWNLOAD,
            MediaStore.Images.Media.IS_DRM,
            MediaStore.Images.Media.IS_PENDING,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.ORIENTATION,
            MediaStore.Images.Media.OWNER_PACKAGE_NAME,
            MediaStore.Images.Media.RELATIVE_PATH,
            MediaStore.Images.Media.RESOLUTION,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.YEAR
        )

        val cursor =
            activity.get()?.contentResolver?.query(contentUri, projection, null, null, null)

        cursor?.use {
            val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val dateAddedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val dateModifiedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
            val dateTakenColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val heightColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            val isDownloadColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_DOWNLOAD)
            val isDrmColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_DRM)
            val isPendingColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_PENDING)
            val mimeTypeColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val orientationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION)
            val ownerPackageNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.OWNER_PACKAGE_NAME)
            val relativePathColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH)
            val resolutionColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RESOLUTION)
            val sizeColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val titleColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
            val widthColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val yearColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.YEAR)

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
                val imageUri = ContentUris.withAppendedId(contentUri, id)

                val imageModel = ImageModel(
                    id = id,
                    displayName = displayName,
                    data = data,
                    bucketDisplayName = bucketDisplayName,
                    bucketId = bucketId,
                    dateAdded = dateAdded,
                    dateModified = dateModified,
                    dateTaken = dateTaken,
                    height = height,
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
                    uri = imageUri
                )

                imageList.add(imageModel)
            }
        }

        return imageList
    }

    fun fetchFromFolder(exactFolderName: String): MutableList<ImageModel> {
        val imageList = mutableListOf<ImageModel>()
        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            // Add other fields as needed
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.IS_DOWNLOAD,
            MediaStore.Images.Media.IS_DRM,
            MediaStore.Images.Media.IS_PENDING,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.ORIENTATION,
            MediaStore.Images.Media.OWNER_PACKAGE_NAME,
            MediaStore.Images.Media.RELATIVE_PATH,
            MediaStore.Images.Media.RESOLUTION,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.YEAR
        )

        val selection = ("${MediaStore.Images.Media.DATA} LIKE ? ")
        val selectionArgs = arrayOf("%/$exactFolderName/%")

        val cursor = activity.get()?.contentResolver?.query(
            contentUri, projection, selection, selectionArgs, null
        )

        cursor?.use {

// Retrieve column indices
            val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val displayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dataColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
            val dateAddedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val dateModifiedColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED)
            val dateTakenColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val heightColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            val isDownloadColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_DOWNLOAD)
            val isDrmColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_DRM)
            val isPendingColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.IS_PENDING)
            val mimeTypeColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val orientationColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION)
            val ownerPackageNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.OWNER_PACKAGE_NAME)
            val relativePathColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH)
            val resolutionColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.RESOLUTION)
            val sizeColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val titleColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)
            val widthColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val yearColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.YEAR)

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
                val imageUri = ContentUris.withAppendedId(contentUri, id)

                val imageModel = ImageModel(
                    id = id,
                    displayName = displayName,
                    data = data,
                    bucketDisplayName = bucketDisplayName,
                    bucketId = bucketId,
                    dateAdded = dateAdded,
                    dateModified = dateModified,
                    dateTaken = dateTaken,
                    height = height,
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
                    uri = imageUri
                )

                imageList.add(imageModel)
            }
        }

        return imageList
    }

    fun fetchFromDefaultHiddenFolder(): MutableList<ImageModel> {
        val imageList = mutableListOf<ImageModel>()
        var pos = 0
        val imageExtensions = listOf("jpg", "jpeg", "png", "webp")
        val pathname = FileUtil.getAppSpecificHiddenFolder(activity)
        val allFiles = pathname?.let {
            val file = File(it)
            if (!file.exists()) return imageList
            file.listFiles { _, name ->
                val extension = name.substringAfterLast(".", "")
                extension.lowercase() in imageExtensions
            }
        }
        allFiles?.map {
            val imageModel = ImageModel(
                id = pos.toLong(), displayName = it.name, data = it.absolutePath
            )
            pos += 1
            imageList.add(imageModel)
        }
        return imageList
    }

    fun fetchFromOtherHiddenFolder(absFilePath: String): MutableList<ImageModel> {
        val imageList = mutableListOf<ImageModel>()
        var pos = 0
        val imageExtensions = listOf("jpg", "jpeg", "png", "webp")
        val file = File(absFilePath)
        if (!file.exists()) return imageList
        val allFiles = file.listFiles { _, name ->
            val extension = name.substringAfterLast(".", "")
            extension.lowercase() in imageExtensions
        }

        allFiles?.map {
            val imageModel = ImageModel(
                id = pos.toLong(), displayName = it.name, data = it.absolutePath
            )
            pos += 1
            imageList.add(imageModel)
        }
        return imageList
    }

}
