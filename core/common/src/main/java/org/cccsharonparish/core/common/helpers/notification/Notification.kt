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
class Notification {

    companion object{
        /**
         * The notification permission constant required for Android Tiramisu (API 35) and later.
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private const val PERMISSION = Manifest.permission.POST_NOTIFICATIONS

        /**
         * Checks if the notification permission is granted on the device.
         *
         * @param context The Android application context.
         * @return `true` if the notification permission is granted, `false` otherwise.
         */
        fun isPermissionGranted(context: Context): Boolean {
            val deviceRequiresNotificationPermission =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            if (deviceRequiresNotificationPermission) {
                return ContextCompat.checkSelfPermission(
                    context,
                    PERMISSION
                ) == PackageManager.PERMISSION_GRANTED
            }
            return true
        }
    }


    /**
     * Request notification permission using a Composable function.
     *
     * @param onResult A callback function that receives the result of the permission request.
     *                 It is invoked with `true` if the permission is granted, and `false` otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun RequestNotificationPermission(onResult: (Boolean) -> Unit) {
        val permissionDialog = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { permissionGranted ->
                onResult(permissionGranted)
            }
        )
        permissionDialog.launch(PERMISSION)
    }
}