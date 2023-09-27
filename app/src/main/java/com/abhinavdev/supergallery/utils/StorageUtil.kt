package com.abhinavdev.supergallery.utils

import android.content.Context
import android.content.SharedPreferences
import com.abhinavdev.supergallery.constants.ActivityNames
import com.abhinavdev.supergallery.constants.SortOrderNames
import com.abhinavdev.supergallery.constants.SortTypeNames

class StorageUtil(val context: Context?) {
    private val shPrefs: SharedPreferences? =
        context?.getSharedPreferences("com.abhinavdev.supergallery", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor? = shPrefs?.edit()

    fun saveActivity(activity: String) {
        editor?.putString("activity", activity)
        editor?.apply()
    }

    fun getActivity(): String? {
        return shPrefs?.getString("activity", ActivityNames.MAIN_ACTIVITY)
    }

    fun saveImageHeight(height: Int) {
        editor?.putInt("image_height", height)
        editor?.apply()
    }

    fun getImageHeight(): Int? {
        return shPrefs?.getInt("image_height", -1)
    }

    /**
     * @param sortType @[SortTypeNames]
     */
    fun saveSortType(sortType: String) {
        editor?.putString("sort_type", sortType)
        editor?.apply()
    }

    fun getSortType(): String? {
        return shPrefs?.getString("sort_type", SortTypeNames.DATE_MODIFIED.value)
    }
    /**
     * @param sortOrder @[SortOrderNames]
     */
    fun saveSortOrder(sortOrder: String) {
        editor?.putString("sort_order", sortOrder)
        editor?.apply()
    }

    fun getSortOrder(): String? {
        return shPrefs?.getString("sort_order", SortOrderNames.DESCENDING.value)
    }
}