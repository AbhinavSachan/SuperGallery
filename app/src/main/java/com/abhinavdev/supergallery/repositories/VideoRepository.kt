package com.abhinavdev.supergallery.repositories

import android.content.Context
import com.abhinavdev.supergallery.interfaces.ImageLoaderCallback
import com.abhinavdev.supergallery.models.VideoModel
import java.lang.ref.WeakReference

interface VideoRepository {
    fun fetchAllVideos(context: WeakReference<Context>,listener:ImageLoaderCallback)

    fun fetchFromFolder(exactFolderName: String,context: WeakReference<Context>,listener:ImageLoaderCallback)

}