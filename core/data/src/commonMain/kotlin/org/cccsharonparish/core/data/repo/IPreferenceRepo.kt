package org.cccsharonparish.core.data.repo

import org.cccsharonparish.core.model.entities.local.NotificationTime

interface IPreferenceRepo {
    fun getUserExitedOnboardingScreen(): Boolean
    suspend fun setUserExitedOnboardingScreen(value: Boolean)
    fun getFontSize(): Float
    suspend fun setFontSize(value: Float)
    fun getNotificationTime(): NotificationTime?
    suspend fun setNotificationTime(value: NotificationTime)
    fun getSelectedContentLanguageCode(): String?
    suspend fun setSelectedContentLanguageCode(value: String)
}