package com.abhinavdev.supergallery.constants

import android.provider.MediaStore

enum class SortTypeNames(val value: String) {
    DATE_ADDED(MediaStore.MediaColumns.DATE_ADDED),
    DATE_MODIFIED(MediaStore.MediaColumns.DATE_MODIFIED),
    DISPLAY_NAME(MediaStore.MediaColumns.DISPLAY_NAME),
}