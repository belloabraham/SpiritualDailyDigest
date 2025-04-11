package screen.home

import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.cccsharonparish.core.common.utils.DateTimeUtil
import org.cccsharonparish.core.data.firestore.Field
import org.cccsharonparish.core.data.repo.IContentRepo
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.domain.error.FirestoreError
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.model.entities.local.SpiritualDailyDigest
import org.cccsharonparish.core.model.entities.remote.RemoteSpiritualDailyDigest

class HomeScreenModel(
    private val preferenceRepo: IPreferenceRepo,
    private val contentRepo: IContentRepo
) : ScreenModel {

    val allPublishedContents: Flow<List<SpiritualDailyDigest>> =
        contentRepo.getALiveListOfAllPublishedContent()

    private var _contentForToday = MutableStateFlow<SpiritualDailyDigest?>(null)
    var contentForToday = _contentForToday.asStateFlow()

    private var _resultForRemoteContentFetching =
        MutableStateFlow<Result<List<RemoteSpiritualDailyDigest>, FirestoreError>>(Result.Empty(null))
    var resultForRemoteContentFetching = _resultForRemoteContentFetching.asStateFlow()

    private var _selectedContentIndex = MutableStateFlow(0)
    var selectedContentIndex = _selectedContentIndex.asStateFlow()

    init {
        allPublishedContents.distinctUntilChanged()
            .onEach { list ->
                val id = contentRepo.getContentIdForToday()
                for ((index, content) in list.withIndex()) {
                    if (content.id == id) {
                        _selectedContentIndex.value = index
                        _contentForToday.value = content
                        break
                    }
                }
            }.launchIn(screenModelScope)
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


    fun setLanguageIndex(value: Int) {
        screenModelScope.launch {
            preferenceRepo.setLanguageIndex(value)
        }
    }

    fun getLanguageIndex(): Int {
        return preferenceRepo.getLanguageIndex()
    }
}