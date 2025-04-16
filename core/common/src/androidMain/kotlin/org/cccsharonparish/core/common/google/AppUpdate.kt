/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.google

import android.app.Activity
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class AppUpdate(applicationContext: Context) {

    private val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
    private lateinit var updateStateListener: InstallStateUpdatedListener
    private var updateType = AppUpdateType.FLEXIBLE


    /**
     * Check if new update have been published for this app on the Google Play Console
     */
    fun checkForUpdate(onNewUpdateAvailable: (updateType: Int, appUpdateInfo: AppUpdateInfo) -> Unit) {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            this.updateType = if (appUpdateInfo.updatePriority() >= UpdatePriority.HIGH.value) {
                AppUpdateType.IMMEDIATE
            } else {
                AppUpdateType.FLEXIBLE
            }

            val updateIsAllowed = isAppUpdateAllowed(updateType, appUpdateInfo)
            val newUpdateIsAvailableForApp =
                appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE

            if (newUpdateIsAvailableForApp && updateIsAllowed) {
                onNewUpdateAvailable(updateType, appUpdateInfo)
            }
        }
    }

    private fun isAppUpdateAllowed(updateType: Int, appUpdateInfo: AppUpdateInfo): Boolean {
        if ((updateType == AppUpdateType.IMMEDIATE) && appUpdateInfo.isUpdateTypeAllowed(
                AppUpdateType.IMMEDIATE
            )
        ) {
            return true
        }
        if ((updateType == AppUpdateType.FLEXIBLE) && appUpdateInfo.isUpdateTypeAllowed(
                AppUpdateType.FLEXIBLE
            )
        ) {
            return true
        }
        return false
    }

    /**
     * Show the user a dialog or an activity notifying the user of an optional or required update respectively
     */
    fun launchUpdateRequestActivityOrDialog(
        appUpdateInfo: AppUpdateInfo,
        updateType: Int,
        activity: Activity,
    ) {
        appUpdateManager.startUpdateFlow(
            appUpdateInfo,
            activity,
            AppUpdateOptions.newBuilder(updateType).build(),
        )
    }

    /**
     * Call this in activity onResume for every entry point activity
     * Checks if any immediate update is running and relaunch the immediate update activity
     */
    fun resumeAnyStalledImmediateUpdate(
        activity: Activity
    ) {
        if (updateType == AppUpdateType.IMMEDIATE) {
            appUpdateManager
                .appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    if (appUpdateInfo.updateAvailability()
                        == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                    ) {
                        // If an in-app update is already running, resume the update.
                        launchUpdateRequestActivityOrDialog(appUpdateInfo, updateType, activity)
                    }
                }
        }
    }

    /**
     * Call this in activity onCreate before calling checkForUpdate()
     * listen for flexible update status to then triggers callback for update complete
     */
    fun registerFlexibleUpdateDownloadListener(
        onFlexibleUpdateDownloaded: () -> Unit,
    ) {
        if (updateType == AppUpdateType.FLEXIBLE) {
            updateStateListener = InstallStateUpdatedListener { state ->
                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                    onFlexibleUpdateDownloaded()
                }
            }
            appUpdateManager.registerListener(updateStateListener)
        }
    }

    /**
     * Call this in activity onResume for every entry point activity
     * listen for flexible update status to then triggers callback for update complete and
     * continues to remember the user just in the case the initial notification from
     * registerFlexibleUpdateDownloadListener fails or was ignored
     */
    fun checkForFlexibleUpdateDownloadState(onFlexibleUpdateDownloaded: () -> Unit) {
        if (updateType == AppUpdateType.FLEXIBLE) {
            appUpdateManager
                .appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        onFlexibleUpdateDownloaded()
                    }
                }
        }
    }


    fun installDownloadedUpdate() {
        appUpdateManager.completeUpdate()
    }

    //Call this in Activity onDestroy
    fun unregisterFlexibleUpdateStatusListener() {
        appUpdateManager.unregisterListener(updateStateListener)
    }

}