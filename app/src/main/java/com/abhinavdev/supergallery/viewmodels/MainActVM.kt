package com.abhinavdev.supergallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.repositories.ImageRepository

class MainActVM(val repository: ImageRepository) : ViewModel() {

    private val _imageList = MutableLiveData<MutableList<ImageModel>>()
    val imageList: LiveData<MutableList<ImageModel>> get() = _imageList

    fun setImageList(list: MutableList<ImageModel>) {
        _imageList.value = list
    }
}