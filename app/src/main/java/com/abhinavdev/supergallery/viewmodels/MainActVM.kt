package com.abhinavdev.supergallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.repositories.ImageRepository

class MainActVM(val repository: ImageRepository) : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageModel>>()
    val imageList: LiveData<List<ImageModel>> get() = _imageList

    fun setImageList(list: List<ImageModel>) {
        _imageList.value = list
    }
}