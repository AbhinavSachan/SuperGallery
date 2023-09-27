package com.abhinavdev.supergallery.repositories

import android.content.Context
import com.abhinavdev.supergallery.interfaces.ImageLoaderCallback
import java.lang.ref.WeakReference

interface HiddenImageRepository {
    fun fetchFromDefaultHiddenFolder(context: WeakReference<Context>, listener: ImageLoaderCallback)
    fun fetchFromOtherHiddenFolder(absFilePath: String, listener: ImageLoaderCallback)
}