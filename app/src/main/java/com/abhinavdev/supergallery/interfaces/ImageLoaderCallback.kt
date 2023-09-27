package com.abhinavdev.supergallery.interfaces

import com.abhinavdev.supergallery.models.ImageModel

interface ImageLoaderCallback {
    fun onLoading() {
    }

    fun <T>onLoadFinished(imageList: MutableList<T>)

}