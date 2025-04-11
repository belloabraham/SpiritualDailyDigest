package screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.cccsharonparish.core.common.utils.DateTimeUtil
import org.cccsharonparish.core.data.firestore.Field
import org.cccsharonparish.core.data.repo.IContentRepo
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.local.Language
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.local.toContentUIState
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest
import org.cccsharonparish.core.model.entities.uistate.LanguageContent
import org.cccsharonparish.core.model.entities.uistate.ContentUIState
import org.cccsharonparish.core.model.entities.uistate.toLanguageContent
import org.cccsharonparish.core.model.entities.uistate.toSupportedLanguages

class HomeScreenModel(
    private val preferenceRepo: IPreferenceRepo,
    private val contentRepo: IContentRepo
) : ScreenModel {

    private val allPublishedContents: Flow<List<SpiritualDailyDigest>> =
        contentRepo.getALiveListOfAllPublishedContent()

    private var _resultForRemoteContentFetching =
        MutableStateFlow<Result<List<RemoteSpiritualDailyDigest>, FirestoreError>>(Result.Empty(null))
    var resultForRemoteContentFetching = _resultForRemoteContentFetching.asStateFlow()

    private var selectedContentIndex = 0

    private var _selectedLanguageCode = MutableStateFlow("en")
    var selectedLanguageCode = _selectedLanguageCode.asStateFlow()

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


    init {
        allPublishedContents.distinctUntilChanged()
            .onEach { publishedContents ->
                val contentIdForToday = contentRepo.getContentIdForToday()
                _listOfContentUIStates.value = publishedContents.map {
                    it.toContentUIState()
                }
                setSelectedContent(contentIdForToday)
            }.launchIn(screenModelScope)
    }


    fun onPrev() {
        selectedContentIndex -= 1
        try {
            val prevContent = listOfContentUIStates.value[selectedContentIndex]
            _contentUIState.value = prevContent
            _contentSupportedLanguages.value = prevContent.toSupportedLanguages()
        } catch (e: Exception) {
            selectedContentIndex += 1
        }
        _showPrevButton.value = selectedContentIndex > 0
    }

    fun onNext() {
        selectedContentIndex += 1
        try {
            val nextContent = listOfContentUIStates.value[selectedContentIndex]
            _contentUIState.value = nextContent
            _contentSupportedLanguages.value = nextContent.toSupportedLanguages()
        } catch (e: Exception) {
            selectedContentIndex -= 1
        }
        _showNextButton.value = selectedContentIndex < listOfContentUIStates.value.size - 1
    }

    suspend fun fetNewPublishedContent() {
        _resultForRemoteContentFetching.value = Result.Loading()
        val date = DateTimeUtil.date()
        val year = date.year
        val month = date.monthNumber
        val result = contentRepo.getRemotePublishedContentsWhere(
            year = year,
            startMonth = month,
            orderBy = Field.MONTH
        )
        _resultForRemoteContentFetching.value = result
        if (result is Result.Success) {
            contentRepo.saveRemotePublishedContents(result.data)
        }
    }

    fun setSelectedContent(selectedContentId:String){
        for ((index, content) in listOfContentUIStates.value.withIndex()) {
            if (content.id == selectedContentId) {
                selectedContentIndex = index
                _contentUIState.value = content
                _contentByLanguage.value =
                    _contentUIState.value!!.toLanguageContent(selectedLanguageCode.value)
                _contentSupportedLanguages.value =
                    _contentUIState.value!!.toSupportedLanguages()
                _showNextButton.value =
                    selectedContentIndex < listOfContentUIStates.value.size - 1
                _showPrevButton.value = selectedContentIndex > 0
                break
            }
        }
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

    }


}