package com.abhinavdev.supergallery.utils

import com.abhinavdev.supergallery.models.ImageModel

object ImageListUtil {

    private var _imageList: MutableList<ImageModel> = mutableListOf()
    private var _position: Int = -1
    var imageList: List<ImageModel>
        get() {
            return _imageList
        }
        set(value) {
            _imageList = ArrayList(value)
        }

    var position: Int
        get() {
            return _position
        }
        set(value) {
            _position = value
        }


    fun addImageInList(position: Int, model: ImageModel) {
        _imageList.add(position, model)
    }

    fun removeImageInList(position: Int) {
        _imageList.removeAt(position)
    }

    fun removeImageInList(model: ImageModel) {
        _imageList.remove(model)
    }

}