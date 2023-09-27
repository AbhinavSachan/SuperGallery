package com.abhinavdev.supergallery

import android.app.Application
import com.abhinavdev.supergallery.interfaces.AppModule
import com.abhinavdev.supergallery.modules.ModuleAppImpl

class ApplicationGallery : Application() {
    companion object{
        lateinit var appModule: AppModule
    }
    override fun onCreate() {
        super.onCreate()
        appModule = ModuleAppImpl()

    }

}