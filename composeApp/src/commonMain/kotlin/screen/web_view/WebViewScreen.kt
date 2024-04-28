package screen.web_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import getNavigationIcon
import org.cccsharonparish.core.resources.iconColor
import org.cccsharonparish.core.ui.Header
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class WebViewScreen(private val uiState: WebViewUIState) : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val state = rememberWebViewState(uiState.url)

        Scaffold(
            topBar = {
                Header(uiState.title, getNavigationIcon()) {
                    navigator?.pop()
                }
            },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .navigationBarsPadding().statusBarsPadding()
        ) {

            Box(Modifier.fillMaxSize().padding(it)) {
                WebView(state)
                if (state.isLoading) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

        }

    }
}