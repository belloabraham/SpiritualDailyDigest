package org.cccsharonparish.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val preferenceRepo = koinInject<IPreferenceRepo>()
        scope.launch {
            preferenceRepo.setUserExitedOnboardingScreen(true)
        }
    }
}