package com.abhinavdev.supergallery.modules

import com.abhinavdev.supergallery.models.ImageModel
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream

class ImageLoader : ModelLoader<ImageModel, InputStream> {
    override fun buildLoadData(
        imagePath: ImageModel,
        width: Int,
        height: Int,
        options: Options
    ): LoadData<InputStream> {
        return LoadData(ObjectKey(imagePath.data), ImageFetcher(imagePath))
    }

    override fun handles(imagePath: ImageModel): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<ImageModel, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<ImageModel, InputStream> {
            return ImageLoader()
        }

        override fun teardown() {}
    }
}