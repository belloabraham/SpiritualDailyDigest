package org.cccsharonparish.core.data.repo

interface IPreferenceRepo {
    fun getUserExitedOnboardingScreen(): Boolean
    suspend fun setUserExitedOnboardingScreen(value: Boolean)
}