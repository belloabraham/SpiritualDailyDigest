package screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import kotlinx.coroutines.launch
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.ui.Header
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.looks_good
import spiritualdailydigest.composeapp.generated.resources.notification_time_title

open class NotificationTimeScreen : Screen {

    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
        ExperimentalResourceApi::class
    )
    @Composable
    override fun Content() {

        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val mediumSize = Size.medium(windowSizeClass)
        val timePickerState = rememberTimePickerState()
        val notificationTimeScreenModel = getScreenModel<NotificationTimeScreenModel>()
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                Header(Res.string.notification_time_title, getNavigationIcon()) {
                    navigator?.pop()
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                Modifier.padding(it).padding(horizontal = mediumSize).padding(bottom = mediumSize)
                    .fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(modifier = Modifier.padding(top = mediumSize), state = timePickerState)

                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    scope.launch {
                        notificationTimeScreenModel.setNotificationTime(
                            timePickerState.hour,
                            timePickerState.minute,
                            timePickerState.isAfternoon
                        )
                        navigator?.pop()
                    }
                }) {
                    Text(stringResource(Res.string.looks_good))
                }
            }
        }
    }
}

expect fun setNotificationTime(hour: Int, minute: Int, isNoon: Boolean)