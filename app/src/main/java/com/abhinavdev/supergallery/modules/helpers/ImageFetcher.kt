package com.abhinavdev.supergallery.modules.helpers

import com.abhinavdev.supergallery.models.ImageModel
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class ImageFetcher(private val model: ImageModel) : DataFetcher<InputStream> {
    private var stream: InputStream? = null
    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        try {
            val file = File(model.data)
            if (file.exists()) {
                val inputStream: InputStream = FileInputStream(file)
                val byteArray = inputStream.readBytes()

                stream = ByteArrayInputStream(byteArray)
                callback.onDataReady(stream)

                inputStream.close() // Close the input stream when done to free resources
            } else {
                // Handle the case where the file doesn't exist
                callback.onLoadFailed(FileNotFoundException("File not found"))
            }
        } catch (e: Exception) {
            callback.onLoadFailed(e)
            e.printStackTrace()
        }

    }

    override fun cleanup() {
        // already cleaned up in loadData and ByteArrayInputStream will be GC'd
        if (stream != null) {
            try {
                stream?.close()
            } catch (ignore: IOException) {
                // can't do much about it
            }
        }
    }

    override fun cancel() {
        // cannot cancel
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }
}