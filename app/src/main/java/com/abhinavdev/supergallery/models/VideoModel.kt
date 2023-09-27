package com.abhinavdev.supergallery.models

import android.net.Uri
import java.io.Serializable

data class VideoModel(
    var id: Long = -1,
    var displayName: String? = "",
    var data: String? = "",
    var bucketDisplayName: String? = "",
    var bucketId: String? = "",
    var dateAdded: Long = -1,
    var dateModified: Long = -1,
    var dateTaken: Long = -1,
    var height: Int = -1,
    var duration: Long = -1,
    var bitrate: Int = -1,
    var isDownload: Int = -1,
    var isDrm: Int = -1,
    var isPending: Int = -1,
    var mimeType: String? = "",
    var orientation: Int = -1,
    var ownerPackageName: String? = "",
    var relativePath: String? = "",
    var resolution: String? = "",
    var size: Long = -1,
    var title: String? = "",
    var width: Int = -1,
    var year: Int = -1,
    var uri: Uri? = null
):Serializable
