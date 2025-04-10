import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import di.appModule
import di.screenModelModule
import org.cccsharonparish.core.common.utils.DateTimeUtil
import org.cccsharonparish.core.data.config.IRemoteConfig
import org.cccsharonparish.core.data.repo.IContentRepo
import org.cccsharonparish.core.data.repo.IPreferenceRepo
import org.cccsharonparish.core.domain.error.Result
import org.cccsharonparish.core.domain.logging.Log
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import screen.home.HomeScreen
import screen.onboarding.getOnboardingScreen
import theme.AppTheme

@Composable
@Preview
fun App(contentId: String?) {
    KoinApplication(application = {
        modules(appModule + screenModelModule)
    }) {

        val remoteConfig = koinInject<IRemoteConfig>()
        val contentRepo = koinInject<IContentRepo>()
        LaunchedEffect(Unit) {
            remoteConfig.initialize(isDebugMode())
            try {
                remoteConfig.fetchAndActive()
            } catch (e: Exception) {
                Log.error(e) {
                    e.message ?: "Error fetching remote config"
                }
            }
            fetchPublishedContent(contentRepo)
        }

        AppTheme(isSystemInDarkTheme()) {
            val userExitedOnboardingScreen =
                koinInject<IPreferenceRepo>().getUserExitedOnboardingScreen()

            val nextScreen =
                if (userExitedOnboardingScreen) HomeScreen(contentId) else getOnboardingScreen(contentId)

            Navigator(nextScreen) { navigator ->
                SlideTransition(navigator)
            }

        }
    }
}

suspend fun fetchPublishedContent(contentRepo:IContentRepo) {
    val savedPublishedContents = contentRepo.getAllPublishedContents()
    if(savedPublishedContents.isEmpty()){
        val year = DateTimeUtil.date().year
        val result =  contentRepo.getRemotePublishedContentsForYear(year)
        if(result is Result.Success){
            contentRepo.saveRemotePublishedContents(result.data)
        }
    }
}


