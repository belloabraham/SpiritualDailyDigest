package screen.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.iconColor
import org.cccsharonparish.core.ui.Header
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.about
import spiritualdailydigest.composeapp.generated.resources.favourites
import spiritualdailydigest.composeapp.generated.resources.notification_time_title

class FavouritesScreen:Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val sizeMedium = Size.medium(windowSizeClass)

        Scaffold(
            topBar = {
                Header(Res.string.favourites, getNavigationIcon()){
                    navigator?.pop()
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background).navigationBarsPadding()
                .statusBarsPadding()
        ) {
            Column(Modifier.padding(it)) {
                HorizontalDivider()
            }

        }
    }
}