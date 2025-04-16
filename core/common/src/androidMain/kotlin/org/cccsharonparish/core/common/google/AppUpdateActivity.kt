/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.google

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import org.cccsharonparish.core.common.extensions.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.cccsharonparish.core.resources.R

open class AppUpdateActivity  : ComponentActivity() {
    private lateinit var appUpdate: AppUpdate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUpdate = AppUpdate(applicationContext)
        appUpdate.registerFlexibleUpdateDownloadListener {
            notifyUserThatFlexibleUpdateDownloadComplete()
        }
        appUpdate.checkForUpdate { updateTye, updateInfo ->
            appUpdate.launchUpdateRequestActivityOrDialog(updateInfo, updateTye, activity = this)
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdate.resumeAnyStalledImmediateUpdate(activity = this)
        appUpdate.checkForFlexibleUpdateDownloadState {
            notifyUserThatFlexibleUpdateDownloadComplete()
        }
    }

    private fun notifyUserThatFlexibleUpdateDownloadComplete() {
        showToast(R.string.app_update_download_complete_msg)
        lifecycleScope.launch {
            delay(10_000)
            appUpdate.installDownloadedUpdate()
        }
    }

    override fun onDestroy() {
        appUpdate.unregisterFlexibleUpdateStatusListener()
        super.onDestroy()
    }
}