package screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.cccsharonparish.core.data.repo.IPreferenceRepo

class HomeScreenModel(private val preferenceRepo: IPreferenceRepo):ScreenModel {
     fun setUserExitedOnboardingScreen(value:Boolean){
        screenModelScope.launch {
            preferenceRepo.setUserExitedOnboardingScreen(value)
        }
    }

    fun setFontSize(value: Float) {
        screenModelScope.launch {
            preferenceRepo.setFontSize(value)
        }
    }

    fun getFontSize(): Float {
        return preferenceRepo.getFontSize()
    }


    fun setLanguageIndex(value: Int) {
        screenModelScope.launch {
            preferenceRepo.setLanguageIndex(value)
        }
    }

    fun getLanguageIndex(): Int {
        return preferenceRepo.getLanguageIndex()
    }
}