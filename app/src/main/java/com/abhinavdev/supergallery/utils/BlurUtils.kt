package com.kratitech.io.ezeedairy.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.View

object BlurUtils {
    private const val BLUR_RADIUS = 25f

    fun applyBlur(context: Context, view: View) {
        val backgroundDrawable = view.background
        if (backgroundDrawable is BitmapDrawable) {
            val blurredBitmap = FastBlur.apply(backgroundDrawable.bitmap, BLUR_RADIUS)
            view.background = BitmapDrawable(context.resources, blurredBitmap)
        }
    }
}

