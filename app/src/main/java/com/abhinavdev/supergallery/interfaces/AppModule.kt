package com.abhinavdev.supergallery.interfaces

import com.abhinavdev.supergallery.repositories.HiddenImageRepository
import com.abhinavdev.supergallery.repositories.HiddenVideoRepository
import com.abhinavdev.supergallery.repositories.ImageRepository
import com.abhinavdev.supergallery.repositories.VideoRepository

interface AppModule {
    val imageRepository: ImageRepository
    val videoRepository: VideoRepository
    val hiddenImageRepository: HiddenImageRepository
    val hiddenVideoRepository: HiddenVideoRepository
}