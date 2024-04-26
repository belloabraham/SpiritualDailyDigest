package screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.cccsharonparish.core.resources.Size
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import screen.home.HomeScreen
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.looks_good
import spiritualdailydigest.composeapp.generated.resources.notification_time_message
import spiritualdailydigest.composeapp.generated.resources.notification_time_title

class OnboardingNotificationTimeScreen: Screen {

    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
        ExperimentalResourceApi::class
    )
    @Composable
    override fun Content() {

        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val largeSize = Size.large(windowSizeClass)
        val mediumSize = Size.medium(windowSizeClass)
        val timePickerState = rememberTimePickerState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(Res.string.notification_time_title))
                    }
                )
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                Modifier.padding(it).padding(horizontal = mediumSize).padding(bottom = mediumSize)
                    .fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(Res.string.notification_time_message))
                    Spacer(Modifier.height(largeSize))
                    TimePicker(timePickerState)
                }

                Button( modifier =  Modifier.fillMaxWidth(), onClick = {
                    navigator?.replace(HomeScreen(""))
                }) {
                    Text(stringResource(Res.string.looks_good))
                }
            }
        }
    }

}