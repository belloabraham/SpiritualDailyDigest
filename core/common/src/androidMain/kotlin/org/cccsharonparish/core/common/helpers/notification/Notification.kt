/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.helpers.notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat

/**
 * A utility class for handling notification-related permissions on Android.
 */
object Notification {
    /**
     * The notification permission constant required for Android Tiramisu (API 35) and later.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    const val PERMISSION = Manifest.permission.POST_NOTIFICATIONS

    /**
     * Checks if the notification permission is granted on the device.
     *
     * @param context The Android application context.
     * @return `true` if the notification permission is granted, `false` otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun isPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

}