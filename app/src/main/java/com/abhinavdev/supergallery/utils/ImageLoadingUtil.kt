package com.abhinavdev.supergallery.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.abhinavdev.supergallery.interfaces.ImageLoadListener
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.modules.GlideApp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.File

object ImageLoadingUtil {

    fun loadFromUri(
        uri: Uri?,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        image_measure: Int = 512
    ) {
        Glide.with(imageView.context).load(uri).override(image_measure)
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadFromUri(
        uri: String?,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        image_measure: Int = 512
    ) {
        Glide.with(imageView.context).load(uri).override(image_measure)
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadFromModel(
        model: ImageModel?,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        image_measure: Int = 512,
    ) {

        GlideApp.with(imageView.context).load(model)
            .override(image_measure)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadImageWithCallback(
        context: Context,
        model: ImageModel?,
        placeholderImage: Int = -1,
        errorHolderImage: Int = placeholderImage,
        image_measure: Int = 512,
        loadListener: ImageLoadListener
    ) {
        val target = object : Target<Drawable> {
            override fun onStart() {

            }

            override fun onStop() {

            }

            override fun onDestroy() {

            }

            override fun onLoadStarted(placeholder: Drawable?) {
                loadListener.onLoadStarted(placeholder)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                loadListener.onLoadFailed(errorDrawable)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                loadListener.onLoadCleared(placeholder)
            }

            override fun getSize(cb: SizeReadyCallback) {

            }

            override fun removeCallback(cb: SizeReadyCallback) {

            }

            override fun setRequest(request: Request?) {

            }

            override fun getRequest(): Request? {
                return null
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                loadListener.onResourceReady(resource, transition)
            }

        }
        GlideApp.with(context).load(model).override(image_measure)
            .placeholder(placeholderImage).error(errorHolderImage).into(target)
    }

    fun loadFromFile(
        uri: File,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        image_measure: Int = 512
    ) {
        Glide.with(imageView.context).load(uri).override(image_measure).centerCrop()
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadFromRes(res: Int, imageView: ImageView, size: Int = 512) {
        Glide.with(imageView.context).load(res).override(size).into(imageView)
    }

}