package screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import isDebugMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.cccsharonparish.core.common.utils.DateTimeUtil
import org.cccsharonparish.core.common.utils.Locale
import org.cccsharonparish.core.data.Constant
import org.cccsharonparish.core.data.config.ConfigKey
import org.cccsharonparish.core.data.config.IRemoteConfig
import org.cccsharonparish.core.data.firestore.Field
import org.cccsharonparish.core.data.repo.IContentRepo
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.domain.Config
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.local.Favourite
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.toContentUIState
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest
import org.cccsharonparish.core.model.entities.uistate.LanguageContent
import org.cccsharonparish.core.model.entities.uistate.ContentUIState
import org.cccsharonparish.core.model.entities.uistate.toLanguageContent
import org.cccsharonparish.core.model.entities.uistate.toSupportedLanguages

class HomeScreenModel(
    private val preferenceRepo: IPreferenceRepo,
    private val contentRepo: IContentRepo,
    private val remoteConfig: IRemoteConfig
) : ScreenModel {

    private val allPublishedContents = contentRepo.getALiveListOfAllPublishedContent()

    private var _resultForEnforcedExplicitUpdate =
        MutableStateFlow<Result<List<RemoteSpiritualDailyDigest>, FirestoreError>>(Result.Empty(null))
    var resultForEnforcedExplicitUpdate = _resultForEnforcedExplicitUpdate.asStateFlow()

    private var selectedContentIndex = 0

    private var _selectedLanguageCode = MutableStateFlow(Config.DEFAULT_CONTENT_LANGUAGE_CODE)
    var selectedLanguageCode = _selectedLanguageCode.asStateFlow()

    private var _contentToShare = MutableStateFlow("")
    var contentToShare = _contentToShare.asStateFlow()

    private var _showNextButton = MutableStateFlow(true)
    var showNextButton = _showNextButton.asStateFlow()

    private var _showPrevButton = MutableStateFlow(false)
    var showPrevButton = _showPrevButton.asStateFlow()

    private var _contentUIState = MutableStateFlow<ContentUIState?>(null)
    var contentUIState = _contentUIState.asStateFlow()

    private var _listOfContentUIStates = MutableStateFlow<List<ContentUIState>>(emptyList())
    private val listOfContentUIStates = _listOfContentUIStates.asStateFlow()

    private var _contentByLanguage = MutableStateFlow<LanguageContent?>(null)
    val contentByLanguage = _contentByLanguage.asStateFlow()

    private var _contentSupportedLanguages = MutableStateFlow<List<Language>>(emptyList())
    val contentSupportedLanguages = _contentSupportedLanguages.asStateFlow()

    private var _isFavourite = MutableStateFlow(true)
    var isFavourite = _isFavourite.asStateFlow()

    private var _bannerUrl = MutableStateFlow<String?>(null)
    var bannerUrl = _bannerUrl.asStateFlow()

    private var _contentIsDueForExplicitUpdate = MutableStateFlow(true)
    var contentIsDueForExplicitUpdate = _contentIsDueForExplicitUpdate.asStateFlow()

    private var _enforcerExplicitUpdate = MutableStateFlow(true)
    var enforceExplicitUpdate = _enforcerExplicitUpdate.asStateFlow()


    init {
        allPublishedContents.distinctUntilChanged()
            .onEach { publishedContents ->
                val contentIdForToday = contentRepo.getContentIdForToday()
                _listOfContentUIStates.value = publishedContents.map {
                    it.toContentUIState()
                }
                val contentForToday = setSelectedContent(contentIdForToday)
                _contentIsDueForExplicitUpdate.value = contentForToday == null
                if (_contentIsDueForExplicitUpdate.value) {
                    _enforcerExplicitUpdate.value = setLastContentInDatabase() == null
                }
                if (!_contentIsDueForExplicitUpdate.value) {
                    updateDatabaseImplicitly()
                }
            }.launchIn(screenModelScope)
    }


    fun onPrev() {
        selectedContentIndex -= 1
        try {
            val prevContent = listOfContentUIStates.value[selectedContentIndex]
            updateUIState(prevContent)
        } catch (e: Exception) {
            selectedContentIndex += 1
        }
        _showPrevButton.value = selectedContentIndex > 0
    }

    fun onNext() {
        selectedContentIndex += 1
        try {
            val nextContent = listOfContentUIStates.value[selectedContentIndex]
            updateUIState(nextContent)
        } catch (e: Exception) {
            selectedContentIndex -= 1
        }
        _showNextButton.value = selectedContentIndex < listOfContentUIStates.value.size - 1
    }

    private fun updateUIState(contentUIState: ContentUIState) {
        _contentUIState.value = contentUIState
        _contentSupportedLanguages.value = contentUIState.toSupportedLanguages()
        setContentByLanguage(_contentUIState.value!!)
        setContentToShare(_contentByLanguage.value!!, contentUIState.id)
        _isFavourite.value = contentRepo.getFavouriteById(contentUIState.id) != null
        _bannerUrl.value = getBannerUrl(contentUIState.imagePath)
    }

    fun updateDatabaseContentExplicitly() {
        _resultForEnforcedExplicitUpdate.value = Result.Loading()
        screenModelScope.launch {
            val result = downloadNewContent()
            _resultForEnforcedExplicitUpdate.value = result
            if(result is Result.Success){
                _contentIsDueForExplicitUpdate.value = false
            }
        }
    }

    private fun updateDatabaseImplicitly() {
        val date = DateTimeUtil.date()
        val year = date.year
        val month = date.monthNumber
        val remainingContent = _listOfContentUIStates.value.filter {
            it.year == year && it.month == month
        }
        if (remainingContent.size < 15) {
            screenModelScope.launch {
                downloadNewContent()
            }
        }
    }

    fun setContentIsDueForExplicitUpdate(value: Boolean) {
        _contentIsDueForExplicitUpdate.value = value
    }

    private suspend fun downloadNewContent(): Result<List<RemoteSpiritualDailyDigest>, FirestoreError> {
        val date = DateTimeUtil.date()
        val year = date.year
        val month = date.monthNumber
        val result = contentRepo.getRemotePublishedContentsWhere(
            year = year,
            startMonth = month,
            orderBy = Field.MONTH
        )
        if (result is Result.Success) {
            contentRepo.saveRemotePublishedContents(result.data)
        }
        return result
    }

    fun setSelectedContent(selectedContentId: String?): ContentUIState? {
        for ((index, content) in listOfContentUIStates.value.withIndex()) {
            if (content.id == selectedContentId) {
                selectedContentIndex = index
                _contentUIState.value = content
                updateUIState(_contentUIState.value!!)
                _showNextButton.value =
                    selectedContentIndex < listOfContentUIStates.value.size - 1
                _showPrevButton.value = selectedContentIndex > 0
                return contentUIState.value
            }
        }
        return null
    }

    private fun setLastContentInDatabase(): ContentUIState? {
        return try {
            val lastContent = listOfContentUIStates.value.last()
            selectedContentIndex = listOfContentUIStates.value.size - 1
            _contentUIState.value = lastContent
            updateUIState(_contentUIState.value!!)
            _showNextButton.value = false
            _showPrevButton.value = true
            lastContent
        } catch (e: Exception) {
            null
        }
    }

    private fun setContentByLanguage(contentUIState: ContentUIState) {
        val languageCode =
            preferenceRepo.getSelectedContentLanguageCode() ?: Locale.getAppLanguageCode()
        var languageContent = contentUIState.toLanguageContent(languageCode)
        _selectedLanguageCode.value = if (languageContent == null) {
            languageContent = contentUIState.toLanguageContent(Config.DEFAULT_CONTENT_LANGUAGE_CODE)
            Config.DEFAULT_CONTENT_LANGUAGE_CODE
        } else {
            languageCode
        }
        _contentByLanguage.value = languageContent
    }

    fun setUserExitedOnboardingScreen(value: Boolean) {
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

    fun onLanguageSelected(language: Language) {
        _selectedLanguageCode.value = language.code!!
        _contentByLanguage.value =
            _contentUIState.value!!.toLanguageContent(selectedLanguageCode.value)
        screenModelScope.launch {
            preferenceRepo.setSelectedContentLanguageCode(language.code!!)
        }
    }

    private fun setContentToShare(languageContent: LanguageContent, contentId: String) {
        val message = languageContent.text!!.message
        _contentToShare.value = "$message \n ${Constant.APP_LINK}/$contentId"
    }

    fun toggleFavouriteState(isFavourite: Boolean) {
        if (isFavourite) {
            removeFromFavourite()
        }
        if (!isFavourite) {
            addToFavourite()
        }
        _isFavourite.value = !isFavourite
    }

    private fun removeFromFavourite() {
        screenModelScope.launch {
            contentUIState.value?.let { contentRepo.removeFromFavouriteById(it.id) }
        }
    }

    private fun addToFavourite() {
        screenModelScope.launch {
            contentUIState.value?.let {
                contentRepo.addToFavourite(Favourite().apply {
                    id = it.id
                    topic = contentByLanguage.value?.text?.topic
                })
            }
        }
    }

    private fun getBannerUrl(imagePath: String?): String {
        if (imagePath != null) {
            val cdnBaseURL = if (isDebugMode()) {
                Config.DEV_BASE_CDN_URL
            } else {
                Config.PROD_BASE_CDN_URL
            }
            return "$cdnBaseURL/$imagePath"
        }
        return remoteConfig.getString(ConfigKey.BANNER_FALLBACK_URL)
    }

}