package com.abhinavdev.supergallery.interfaces

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.transition.Transition

interface GlideImageLoadListener {
    fun onLoadStarted(placeholder: Drawable?)

    fun onLoadFailed(errorDrawable: Drawable?)

    fun onLoadCleared(placeholder: Drawable?)

    fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?)
}