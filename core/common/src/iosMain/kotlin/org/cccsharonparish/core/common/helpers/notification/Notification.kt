package org.cccsharonparish.core.common.helpers.notification

import org.cccsharonparish.core.domain.logging.Log
import platform.UIKit.UIApplication
import platform.UIKit.registerForRemoteNotifications
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNNotificationSettings
import platform.UserNotifications.UNUserNotificationCenter
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

object Notification {

    fun checkNotificationPermission(onResult: (Boolean) -> Unit) {
        UNUserNotificationCenter.currentNotificationCenter().getNotificationSettingsWithCompletionHandler { settings: UNNotificationSettings? ->
            val status = settings?.authorizationStatus
            val hasPermission = status == UNAuthorizationStatusAuthorized
            onResult(hasPermission)
        }
    }

    fun requestNotificationPermission(onResult: (Boolean) -> Unit) {
        UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptions(
            options = (UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge)
        ) { granted, error ->
            if (granted) {
                dispatch_async(dispatch_get_main_queue()) {
                    UIApplication.sharedApplication().registerForRemoteNotifications()
                }
            } else {
               error?.let {
                   Log.error (it.localizedDescription)
               }
            }
            onResult(granted)
        }
    }
}