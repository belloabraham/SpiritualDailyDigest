package screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.cccsharonparish.core.data.repo.IPreferenceRepo

class HomeScreenModel(private val preferenceRepo: IPreferenceRepo):ScreenModel {
    suspend fun setUserExitedOnboardingScreen(value:Boolean){
        screenModelScope.launch {
            preferenceRepo.setUserExitedOnboardingScreen(value)
        }
    }
}