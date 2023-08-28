package com.abhinavdev.supergallery.utils

import android.content.Context
import android.content.SharedPreferences
import com.abhinavdev.supergallery.constants.ActivityNames

class StorageUtil(val context: Context) {
    private val shPrefs: SharedPreferences =
        context.getSharedPreferences("com.abhinavdev.supergallery", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = shPrefs.edit()

    fun saveActivity(activity: String) {
        editor.putString("activity", activity)
        editor.apply()
    }

    fun getActivity(): String? {
        return shPrefs.getString("activity", ActivityNames.MAIN_ACTIVITY)
    }

    fun saveImageHeight(activity: Int) {
        editor.putInt("image_height", activity)
        editor.apply()
    }

    fun getImageHeight(): Int {
        return shPrefs.getInt("image_height", -1)
    }
}