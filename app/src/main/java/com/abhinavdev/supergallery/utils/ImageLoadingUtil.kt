package com.abhinavdev.supergallery.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.abhinavdev.supergallery.interfaces.GlideImageLoadListener
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.modules.GlideApp
import com.bumptech.glide.Glide
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
        overrideSize:Boolean = true,
        imageMeasure: Int = 512
    ) {
        if (overrideSize) {
            Glide.with(imageView.context).load(uri).override(imageMeasure)
                .skipMemoryCache(true)
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        } else {
            Glide.with(imageView.context).load(uri)
                .skipMemoryCache(true)
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        }
    }

    fun loadFromUri(
        uri: String?,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        imageMeasure: Int = 512
    ) {
        Glide.with(imageView.context).load(uri).override(imageMeasure)
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadFromModel(
        model: ImageModel?,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        imageMeasure: Int = 512,
    ) {
        GlideApp.with(imageView.context).load(model)
            .override(imageMeasure)
            .skipMemoryCache(true)
            .centerCrop()
            .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
    }

    fun loadImageWithCallback(
        context: Context,
        model: ImageModel?,
        placeholderImage: Int = -1,
        errorHolderImage: Int = placeholderImage,
        imageMeasure: Int = 512,
        loadListener: GlideImageLoadListener
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
        GlideApp.with(context).load(model).override(imageMeasure)
            .placeholder(placeholderImage).error(errorHolderImage).into(target)
    }

    fun loadFromFile(
        file: File,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        overrideSize:Boolean = true,
        imageMeasure: Int = 512
    ) {
        if (overrideSize) {
            Glide.with(imageView.context).load(file).override(imageMeasure)
                .skipMemoryCache(true)
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        } else {
            Glide.with(imageView.context).load(file)
                .skipMemoryCache(true)
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        }

    }
    fun loadFromBitmap(
        bitmap: Bitmap,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        overrideSize:Boolean = true,
        imageMeasure: Int = 512
    ) {
        if (overrideSize) {
            Glide.with(imageView.context).load(bitmap).override(imageMeasure).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        } else {
            Glide.with(imageView.context).load(bitmap).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        }
    }
    fun loadFromDrawable(
        drawable: Drawable,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        overrideSize:Boolean = true,
        imageMeasure: Int = 512
    ) {
        if (overrideSize) {
            Glide.with(imageView.context).load(drawable).override(imageMeasure).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        } else {
            Glide.with(imageView.context).load(drawable).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)

        }
    }
    fun loadFromBitmapDrawable(
        bitmapDrawable: BitmapDrawable,
        placeholderImage: Int,
        errorHolderImage: Int = placeholderImage,
        imageView: ImageView,
        overrideSize:Boolean = true,
        imageMeasure: Int = 512
    ) {
        if (overrideSize) {
            Glide.with(imageView.context).load(bitmapDrawable).override(imageMeasure).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)
        } else {
            Glide.with(imageView.context).load(bitmapDrawable).centerCrop()
                .placeholder(placeholderImage).error(errorHolderImage).into(imageView)

        }
    }

    fun loadFromRes(res: Int, imageView: ImageView, size: Int = 512) {
        Glide.with(imageView.context).load(res).override(size).into(imageView)
    }

}