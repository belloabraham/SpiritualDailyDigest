package org.cccsharonparish.core.data.repo

interface IPreferenceRepo {
    fun getUserExitedOnboardingScreen(): Boolean
    suspend fun setUserExitedOnboardingScreen(value: Boolean)
    fun getFontSize(): Float
    suspend fun setFontSize(value: Float)

    fun getLanguageIndex(): Int

    suspend fun setLanguageIndex(value:Int)
}