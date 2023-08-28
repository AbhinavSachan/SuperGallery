package com.abhinavdev.supergallery.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtil {
    const val PERMISSION_REQUEST_CODE = 212652

    private fun checkPermission(
        context: Context,
        permissions: List<String>
    ): Map<String, Boolean> {
        val permissionResults = mutableMapOf<String, Boolean>()
        for (permission in permissions) {
            permissionResults[permission] = ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
        return permissionResults
    }

    /**
     * this method will check the all the permissions and request them if needed
     */
    fun arePermissionsNotRequired(activity: Activity, permissions: List<String>): Boolean {
        val checkedPermission = checkPermission(activity, permissions)
        val perForRequest = checkedPermission.filterValues { !it }.keys.toList()
        return if (perForRequest.isEmpty()) {
            true
        } else {
            ActivityCompat.requestPermissions(
                activity,
                perForRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
            false
        }
    }
}