package screen.onboarding

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.cccsharonparish.core.data.repo.IPreferenceRepo

class OnboardingScreenModel(private val preferenceRepo: IPreferenceRepo) : ScreenModel {

    fun setReferrerContentId(contentId: String) {
        screenModelScope.launch {
            preferenceRepo.setReferrerContentId(contentId)
        }
    }
}