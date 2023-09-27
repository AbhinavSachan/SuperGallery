package com.abhinavdev.supergallery.repositories

import android.content.Context
import com.abhinavdev.supergallery.interfaces.ImageLoaderCallback
import java.lang.ref.WeakReference

interface ImageRepository {
    /**
     * it internally runs on background thread so run it on main thread without any concern
     */
    fun fetchAllImages(context: WeakReference<Context>,listener: ImageLoaderCallback)

    /**
     * it internally runs on background thread so run it on main thread without any concern
     */
    fun fetchFromFolder(exactFolderName: String,context: WeakReference<Context>,listener: ImageLoaderCallback)
}