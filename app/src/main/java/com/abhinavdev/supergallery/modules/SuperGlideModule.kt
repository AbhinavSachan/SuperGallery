package com.abhinavdev.supergallery.modules

import android.content.Context
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.modules.helpers.ImageLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

@GlideModule
class SuperGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(
            ImageModel::class.java,
            InputStream::class.java,
            ImageLoader.Factory()
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}