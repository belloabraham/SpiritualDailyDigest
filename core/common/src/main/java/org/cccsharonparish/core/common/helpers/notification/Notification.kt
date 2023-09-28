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

class Notification {

    companion object{
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private const val PERMISSION = Manifest.permission.POST_NOTIFICATIONS
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