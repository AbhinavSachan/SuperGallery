package com.abhinavdev.supergallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhinavdev.supergallery.models.ImageModel

class ImageViewPagerVM : ViewModel() {


    private val _imageList = MutableLiveData<List<ImageModel>>(mutableListOf())
    val imageList: LiveData<List<ImageModel>> get() = _imageList

    fun setImageList(list: List<ImageModel>) {
        _imageList.value = list
    }

}