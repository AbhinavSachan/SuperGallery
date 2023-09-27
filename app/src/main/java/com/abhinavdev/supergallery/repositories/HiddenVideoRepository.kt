package com.abhinavdev.supergallery.repositories

import android.content.Context
import com.abhinavdev.supergallery.interfaces.ImageLoaderCallback
import com.abhinavdev.supergallery.models.VideoModel
import java.lang.ref.WeakReference

interface HiddenVideoRepository {
    fun fetchFromDefaultHiddenFolder(context: WeakReference<Context>,listener: ImageLoaderCallback)

    fun fetchFromOtherHiddenFolder(absFilePath: String,listener:ImageLoaderCallback)

}