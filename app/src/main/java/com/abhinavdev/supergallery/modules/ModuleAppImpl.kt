package com.abhinavdev.supergallery.modules

import com.abhinavdev.supergallery.interfaces.AppModule
import com.abhinavdev.supergallery.repositories.HiddenImageRepository
import com.abhinavdev.supergallery.repositories.HiddenVideoRepository
import com.abhinavdev.supergallery.repositories.ImageRepository
import com.abhinavdev.supergallery.repositories.VideoRepository
import com.abhinavdev.supergallery.repositories.implementations.ImgRepoImpl
import com.abhinavdev.supergallery.repositories.implementations.VdoRepoImpl

class ModuleAppImpl() : AppModule {
    override val imageRepository: ImageRepository by lazy {
        ImgRepoImpl()
    }
    override val videoRepository: VideoRepository by lazy {
        VdoRepoImpl()
    }
    override val hiddenImageRepository: HiddenImageRepository by lazy {
        ImgRepoImpl()
    }
    override val hiddenVideoRepository: HiddenVideoRepository by lazy {
        VdoRepoImpl()
    }
}