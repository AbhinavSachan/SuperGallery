package com.kratitech.io.ezeedairy.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

object FastBlur {
    fun apply(image: Bitmap, radius: Float): Bitmap {
        val bitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true

        val scale = 1f / radius
        val width = image.width
        val height = image.height
        val matrix = android.graphics.Matrix()
        matrix.setScale(scale, scale)
        val scaledBitmap = Bitmap.createBitmap(
            image, 0, 0, width, height, matrix, true
        )

        val iterations = 4
        val divide = 1 shl (iterations - 1)
        val widthScale = width.toFloat() / divide
        val heightScale = height.toFloat() / divide

        for (iteration in 0 until iterations) {
            val blurRadius = radius * (1 + iteration.toFloat() * 0.5f)
            paint.alpha = (255 * (iterations - iteration) / iterations.toFloat()).toInt()

            for (x in 0 until iterations) {
                for (y in 0 until iterations) {
                    canvas.save()
                    canvas.translate(x * widthScale, y * heightScale)
                    canvas.drawBitmap(scaledBitmap, 0f, 0f, paint)
                    canvas.restore()
                }
            }

            doBlur(bitmap, blurRadius.toInt())
        }

        return bitmap
    }

    private external fun doBlur(bitmap: Bitmap, radius: Int)

    init {
        System.loadLibrary("native-lib")
    }
}
