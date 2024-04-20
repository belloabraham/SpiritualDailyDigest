package screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.coroutines.launch

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        LaunchedEffect(Unit){
            screenModel.setUserExitedOnboardingScreen(true)
        }
    }
}